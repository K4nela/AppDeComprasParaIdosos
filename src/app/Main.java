package app;

import dao.Conexao;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = Conexao.createConnectionToMySQL();
    }
}
