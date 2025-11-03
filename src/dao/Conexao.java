package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //caminho do banco de dados, porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/easypeasy";
    //nome do usuario
    private static final String USERNAME = "root";
    //senha do banco de dados
    private static final String PASSWORD = "1234";

    //Criando conexão com o banco de dados

    public static Connection createConnectionToMySQL() throws Exception {
        //Carregando a classe pela jvm
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            //Criando conexão com o banco de dados
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Banco de dados conectado com sucesso!");
            return connection;

        } catch (SQLException e) {
            //Erro para informar falha na conexão com o banco de dados
            throw new RuntimeException("ERRO! Não foi possível conectar ao banco de dados!", e);
        }
    }

    public static void closeConnection(Connection conn) throws SQLException {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
//