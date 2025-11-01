package dao;

import model.itens;
import model.listaDeCompras;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemListaDAO {

    private Connection conn;

    public ItemListaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(itens item) throws SQLException {
        String sql = "INSERT INTO itens (id_lista, nome_iten, descricao, quantidade, nome_loja, link) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, item.getId_lista().getId_lista());
        stmt.setString(2, item.getNome_iten());
        stmt.setString(3, item.getDescricao());
        stmt.setInt(4, item.getQuantidade());
        stmt.setString(5, item.getNome_loja());
        stmt.setString(6, item.getLink());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<itens> getAll() throws SQLException {
        List<itens> listaItens = new ArrayList<>();
        String sql = "SELECT * FROM itens";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            itens item = new itens();
            item.setId_iten(rs.getInt("id_iten"));
            item.setId_lista(new listaDeCompras(rs.getInt("id_lista")));
            item.setNome_iten(rs.getString("nome_iten"));
            item.setDescricao(rs.getString("descricao"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setNome_loja(rs.getString("nome_loja"));
            item.setLink(rs.getString("link"));
            listaItens.add(item);
        }

        rs.close();
        stmt.close();
        return listaItens;
    }

    public itens getById(int id) throws SQLException {
        String sql = "SELECT * FROM itens WHERE id_iten = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        itens item = null;
        if (rs.next()) {
            item = new itens();
            item.setId_iten(rs.getInt("id_iten"));
            item.setId_lista(new listaDeCompras(rs.getInt("id_lista")));
            item.setNome_iten(rs.getString("nome_iten"));
            item.setDescricao(rs.getString("descricao"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setNome_loja(rs.getString("nome_loja"));
            item.setLink(rs.getString("link"));
        }

        rs.close();
        stmt.close();
        return item;
    }

    public void update(itens item) throws SQLException {
        String sql = "UPDATE itens SET id_lista=?, nome_iten=?, descricao=?, quantidade=?, nome_loja=?, link=? WHERE id_iten=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, item.getId_lista().getId_lista());
        stmt.setString(2, item.getNome_iten());
        stmt.setString(3, item.getDescricao());
        stmt.setInt(4, item.getQuantidade());
        stmt.setString(5, item.getNome_loja());
        stmt.setString(6, item.getLink());
        stmt.setInt(7, item.getId_iten());
        stmt.executeUpdate();
        stmt.close();
    }


    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM itens WHERE id_iten=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
