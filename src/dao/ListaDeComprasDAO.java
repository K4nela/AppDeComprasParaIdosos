package dao;

import model.listaDeCompras;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeComprasDAO extends CrudDAO<listaDeCompras> {
    private Connection conn;

    public ListaDeComprasDAO(Connection conn) {
        super(conn);
        this.conn = conn;
    }

    public listaDeCompras getById(int id) throws SQLException {
        String sql = "SELECT * FROM lista_de_compras WHERE id_lista = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        listaDeCompras lista = null;
        if (rs.next()) {
            lista = new listaDeCompras();
            lista.setId_lista(rs.getInt("id_lista"));
            lista.setId_idoso(new model.idoso(rs.getInt("id_idoso")));
            lista.setNomeLista(rs.getString("nome"));
            lista.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
            lista.setDescricao(rs.getString("descricao"));
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public void update(int id) throws SQLException {
        listaDeCompras lista = new listaDeCompras();

        String sql = "UPDATE lista_de_compras SET id_idoso=?, nome=?, dataCriacao=?, descricao=? WHERE id_lista= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, lista.getId_idoso());
        ps.setString(2, lista.getNomeLista());
        ps.setDate(3, Date.valueOf(lista.getDataCriacao()));
        ps.setString(4, lista.getDescricao());
        ps.setInt(5, id);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void save(listaDeCompras lista) throws SQLException {
        String sql = "INSERT INTO lista_de_compras (id_idoso, nome, dataCriacao, descricao) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, lista.getId_idoso());
        ps.setString(2, lista.getNomeLista());
        ps.setDate(3, Date.valueOf(lista.getDataCriacao()));
        ps.setString(4, lista.getDescricao());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<listaDeCompras> get() throws SQLException {
        List<listaDeCompras> listas = new ArrayList<>();
        String sql = "SELECT * FROM lista_de_compras";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            listaDeCompras lista = new listaDeCompras();
            lista.setId_lista(rs.getInt("id_lista"));
            lista.setId_idoso(new model.idoso(rs.getInt("id_idoso")));
            lista.setNomeLista(rs.getString("nome"));
            lista.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
            lista.setDescricao(rs.getString("descricao"));
            listas.add(lista);
        }
        rs.close();
        ps.close();
        return listas;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM lista_de_compras WHERE id_lista=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}
