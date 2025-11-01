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

    //interface generica para transformar tuplas em objetos
    public interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    //metodo para salvar
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

    //metodo para salvar tipo de usuario
    public void saveTipo(String tabela, int idUsuario) throws SQLException {
        String sql = "INSERT INTO " + tabela + " (id_usuario) VALUES (?);";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, idUsuario);
            psmt.executeUpdate();
        }
    }

    //metodo para fazer update
    public void update (String sql, Object... params) throws SQLException{
        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            for(int i = 0 ; i < params.length; i++ ){
                psmt.setObject(i + 1, params[i]);
            }
            psmt.executeUpdate();
        }
    }

    //metodo para mostrar informações
    public List<T> get (String sql, ResultSetMapper<T> mapper, Object... params) throws SQLException{
        List<T>  lista = new ArrayList<>();

        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            for(int i = 0 ; i < params.length; i++ ){
                psmt.setObject(i + 1, params[i]);
            }
            try(ResultSet rlts = psmt.executeQuery()){
                while(rlts.next()){
                    lista.add(mapper.map(rlts));
                }
            }
        }
        return lista;
    }


    //metodo para deletar
    public void delete (String sql, Object... params) throws SQLException{
        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            for(int i = 0 ; i < params.length; i++ ){
                psmt.setObject(i + 1, params[i]);
            }
            psmt.executeUpdate();
        }
    }
}
