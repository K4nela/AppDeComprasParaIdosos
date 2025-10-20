package dao;

import model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MonitroaDAO {

    /*
    Desenvolvendo metodo de monitoramento do DAO (Associando tabela de usuários idosos e familiares).
     */
    public void monitoraDAO(int idFamiliar, int idIdoso) throws Exception {
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        String sql = "INSERT INTO monitora (id_familiar, id_idoso) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idFamiliar);
            ps.setInt(2, idIdoso);
            ps.executeUpdate();

            System.out.println("Associação entre familiar e idoso realizada com sucesso!");
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível associar familiar e idoso: " + e.getMessage());
        }
    }

    /*
    Desenvolvendo metodo para listar os familiares de um idoso e vice-versa.
     */
    public List<usuario> listarMonitora(int familiar, int idIdoso) {

        return List.of(); // Retornar lista de familiares
    }
}
