package dao;

import model.status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    private Connection conn;

    public StatusDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(status stat) throws SQLException {
        String sql = "INSERT INTO status (id_status) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, stat.getId_status());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<status> getAll() throws SQLException {
        List<status> listaStatus = new ArrayList<>();
        String sql = "SELECT * FROM status";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            status stat = new status(rs.getInt("id_status"));
            listaStatus.add(stat);
        }

        rs.close();
        stmt.close();
        return listaStatus;
    }

    public status getById(int id) throws SQLException {
        String sql = "SELECT * FROM status WHERE id_status = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        status stat = null;
        if (rs.next()) {
            stat = new status(rs.getInt("id_status"));
        }

        rs.close();
        stmt.close();
        return stat;
    }

    public void update(status stat, int novoId) throws SQLException {
        String sql = "UPDATE status SET id_status=? WHERE id_status=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, novoId);
        stmt.setInt(2, stat.getId_status());
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM status WHERE id_status=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}

