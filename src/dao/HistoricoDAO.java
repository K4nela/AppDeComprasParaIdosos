package dao;

import model.historico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    private Connection conn;

    public HistoricoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(historico hist) throws SQLException {
        String sql = "INSERT INTO historico (data_historico, id_item, id_status) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(hist.getData_historico().getTime()));
        stmt.setInt(2, hist.getId_item());
        stmt.setInt(3, hist.getId_status());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<historico> getAll() throws SQLException {
        List<historico> listaHist = new ArrayList<>();
        String sql = "SELECT * FROM historico";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            historico hist = new historico();
            hist.setId_histrico(rs.getInt("id_histrico"));
            hist.setData_historico(rs.getDate("data_historico"));
            hist.setId_item(rs.getInt("id_item"));
            hist.setId_status(rs.getInt("id_status"));
            listaHist.add(hist);
        }

        rs.close();
        stmt.close();
        return listaHist;
    }

    public historico getById(int id) throws SQLException {
        String sql = "SELECT * FROM historico WHERE id_histrico = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        historico hist = null;
        if (rs.next()) {
            hist = new historico();
            hist.setId_histrico(rs.getInt("id_histrico"));
            hist.setData_historico(rs.getDate("data_historico"));
            hist.setId_item(rs.getInt("id_item"));
            hist.setId_status(rs.getInt("id_status"));
        }

        rs.close();
        stmt.close();
        return hist;
    }

    public void update(historico hist) throws SQLException {
        String sql = "UPDATE historico SET data_historico=?, id_item=?, id_status=? WHERE id_histrico=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(hist.getData_historico().getTime()));
        stmt.setInt(2, hist.getId_item());
        stmt.setInt(3, hist.getId_status());
        stmt.setInt(4, hist.getId_histrico());
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM historico WHERE id_histrico=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
