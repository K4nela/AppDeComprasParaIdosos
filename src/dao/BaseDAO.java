package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class BaseDAO<T> implements Base<T>{
    protected Connection conn;
    protected PreparedStatement psmt;
    Scanner scn = new Scanner(System.in);


    //Construtor para definir a conexão
    public BaseDAO(Connection conn) {
        this.conn = conn;
    }

    //interface generica para transformar tuplas em objetos
    public interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    //Metodo para salvar
    public void save (String sql, Object... params) throws SQLException{
        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            for(int i = 0 ; i < params.length; i++ ){
                psmt.setObject(i + 1, params[i]);
            }
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
