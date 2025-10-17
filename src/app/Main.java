package app;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.Scanner;

import static controller.CadastroController.telaCadastro;
import static view.Menus.*;
import static controller.HomeController.telaHome;
import static controller.LoginController.telaLogin;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static usuario logado = null;
    static int opcao;

    public static void main(String[] args) {


        try {
            Connection conn = Conexao.createConnectionToMySQL();//Conecta no banco de dados
            UsuarioDAO UsuarioDao = new UsuarioDAO(conn);//Instancia o DAO

            do {
                menuLogin();
                String opcaoTexto = scn.nextLine();

                try {
                    opcao = Integer.parseInt(opcaoTexto);
                } catch (NumberFormatException e) {
                    System.out.println("ERRO! Entrada inválida, insira um número.");
                    opcao = -1;
                    continue;
                }

                switch (opcao) {
                    case 1:
                        logado = telaLogin();

                        if(logado != null){
                            telaHome(logado, UsuarioDao, conn);
                        }else{
                            System.out.println("ERRO! Email ou senha incorretos.");
                        }

                        break;

                    case 2:
                        telaCadastro();
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
        }
    }
}
