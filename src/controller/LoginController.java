package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.Scanner;

public final class LoginController {

    public static usuario telaLogin() throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO UsuarioDao = new UsuarioDAO(conn);
        usuario Usuario = null;

        while (Usuario == null) {
            System.out.println("Digite [0] para voltar.");
            System.out.println("--------Tela De Login--------");
            System.out.print("email: ");
            String email = scn.nextLine();
            if (email.equals("0")) {
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            System.out.print("Senha: ");
            String senha = scn.nextLine();
            if(senha.equals("0")){
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            Usuario = UsuarioDao.login(email, senha);

            if (Usuario != null) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem vindo, " + Usuario.getNome() + ", " + Usuario.getTipo() + "!");
                System.out.println("-----------------------------");
                return Usuario;

            }else{
                System.out.println("ERRO! Email ou senha incorretos.");
                System.out.println("-----------------------------");
            }
        }
        return Usuario;
    }
}
