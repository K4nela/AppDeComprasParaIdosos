package dao;

import model.listaDeCompras;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeComprasDAO {
    private Connection conn;

    public ListaDeComprasDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(listaDeCompras lista) throws SQLException {
        String sql = "INSERT INTO lista_de_compras (id_idoso, nomeLista, dataCriacao, observacoes) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, lista.getId_idoso().getId());
        stmt.setString(2, lista.getNomeLista());
        stmt.setDate(3, Date.valueOf(lista.getDataCriacao()));
        stmt.setString(4, lista.getObservacoes());
        stmt.executeUpdate();
        stmt.close();
    }


    public List<listaDeCompras> getAll() throws SQLException {
        List<listaDeCompras> listas = new ArrayList<>();
        String sql = "SELECT * FROM lista_de_compras";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            listaDeCompras lista = new listaDeCompras();
            lista.setId_lista(rs.getInt("id_lista"));
            // Aqui você pode instanciar Idoso apenas com o ID
            lista.setId_idoso(new model.Idoso(rs.getInt("id_idoso")));
            lista.setNomeLista(rs.getString("nomeLista"));
            lista.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
            lista.setObservacoes(rs.getString("observacoes"));
            listas.add(lista);
        }
        rs.close();
        stmt.close();
        return listas;
    }

    public listaDeCompras getById(int id) throws SQLException {
        String sql = "SELECT * FROM lista_de_compras WHERE id_lista = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        listaDeCompras lista = null;
        if (rs.next()) {
            lista = new listaDeCompras();
            lista.setId_lista(rs.getInt("id_lista"));
            lista.setId_idoso(new model.Idoso(rs.getInt("id_idoso")));
            lista.setNomeLista(rs.getString("nomeLista"));
            lista.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
            lista.setObservacoes(rs.getString("observacoes"));
        }
        rs.close();
        stmt.close();
        return lista;
    }

    public void update(listaDeCompras lista) throws SQLException {
        String sql = "UPDATE lista_de_compras SET id_idoso=?, nomeLista=?, dataCriacao=?, observacoes=? WHERE id_lista=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, lista.getId_idoso().getId());
        stmt.setString(2, lista.getNomeLista());
        stmt.setDate(3, Date.valueOf(lista.getDataCriacao()));
        stmt.setString(4, lista.getObservacoes());
        stmt.setInt(5, lista.getId_lista());
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM lista_de_compras WHERE id_lista=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
