package dao;

import model.historico;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    private Connection conn;

    public HistoricoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(historico hist) throws SQLException {
        String sql = "INSERT INTO historico (data_historico, id_item, id_status) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(hist.getData_historico()));
        ps.setInt(2, hist.getId_item());
        ps.setInt(3, hist.getId_status());
        ps.executeUpdate();
        ps.close();
    }

    public List<historico> getAll() throws SQLException {
        List<historico> listaHist = new ArrayList<>();
        String sql = "SELECT * FROM historico";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            historico hist = new historico();
            hist.setId_histrico(rs.getInt("id_histrico"));
            hist.setData_historico(rs.getDate("data_historico").toLocalDate());
            hist.setId_item(rs.getInt("id_item"));
            hist.setId_status(rs.getInt("id_status"));
            listaHist.add(hist);
        }

        rs.close();
        ps.close();
        return listaHist;
    }

    public historico getById(int id) throws SQLException {
        String sql = "SELECT * FROM historico WHERE id_histrico = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        historico hist = null;

        if (rs.next()) {
            hist = new historico();
            hist.setId_histrico(rs.getInt("id_histrico"));
            hist.setData_historico(rs.getDate("data_historico").toLocalDate());
            hist.setId_item(rs.getInt("id_item"));
            hist.setId_status(rs.getInt("id_status"));
        }

        rs.close();
        ps.close();
        return hist;
    }

    public void update(historico hist) throws SQLException {
        String sql = "UPDATE historico SET data_historico=?, id_item=?, id_status=? WHERE id_histrico=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(hist.getData_historico()));
        ps.setInt(2, hist.getId_item());
        ps.setInt(3, hist.getId_status());
        ps.setInt(4, hist.getId_histrico());
        ps.executeUpdate();
        ps.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM historico WHERE id_histrico=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}
