package view;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.Scanner;

public class TelaLogin {

    public static int telaLogin(int opcao) throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO UsuarioDao = new UsuarioDAO(conn);

        try {
            System.out.println("--------Tela-De-Login--------");
            System.out.print("email: ");
            String email = scn.nextLine();

            System.out.print("Senha: ");
            String senha = scn.nextLine();

            usuario Usuario = UsuarioDao.login(email, senha);

            if (Usuario != null) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem vindo, " + Usuario.getNome() + "!");
                System.out.println("-----------------------------");
                return opcao = 0;
            } else {
                System.out.println("ERRO! Usuário ou senha incorretos!");
            }
        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível realizar o login!");
        }
        return opcao;
    }
}
