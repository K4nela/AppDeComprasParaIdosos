package dao;

import java.sql.*;
import java.util.*;

//
public abstract class CrudDAO<T> implements CrudInterface<T> {
    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rlts;

    Scanner scn = new Scanner(System.in);

    //Construtor para definir a conexão
    public CrudDAO(Connection conn) {
        this.conn = conn;
    }

    //interface para transformar tuplas em objetos
    public interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    //metodo com parametro generalizado  para salvar
    public int save(String sql, Object... params) throws SQLException {
        int generatedId = -1;
        try (PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
            psmt.executeUpdate();

            try (ResultSet rs = psmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1); // pega a chave gerada
                }
            }
        }
        return generatedId;
    }

    //metodo com parametro generalizado  para fazer update
    public void update(String sql, Object... params) throws SQLException {
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
            psmt.executeUpdate();
        }
    }

    //metodo com parametro generalizado  para mostrar informações
    public List<T> get(String sql, ResultSetMapper<T> mapper, Object... params) throws SQLException {
        List<T> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapper.map(rs));
                }
            }
        }
        return lista;
    }

    //metodo com parametro generalizado para deletar
    public void delete(String sql, Object... params) throws SQLException {
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
            psmt.executeUpdate();
        }
    }
}
