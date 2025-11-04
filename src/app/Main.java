package app;

import dao.Conexao;
import model.usuario;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static controller.CadastroController.telaCadastro;
import static dao.Conexao.closeConnection;
import static view.Menus.*;
import static controller.HomeController.telaHome;
import static controller.LoginController.telaLogin;

public class Main {
    //definindo variáveis globais para evitar múltiplas instâncias
    static Scanner scn = new Scanner(System.in);
    static int opcao;
    static usuario logado = null;

    public static void main(String[] args) {
        Connection conn = null; //instanciando uma futura conexão com o banco de dados

        try {
            conn = Conexao.createConnectionToMySQL();//Conectando no banco de dados

            while (true) {//iniciando um looping para a aplicação rodar enquando o usuário não escolher sair

                menuLogin();
                opcao = scn.nextInt();
                scn.nextLine();

                try {
                    switch (opcao) {
                        //
                        case 1 -> {//login - chama o metodo que retorna um usuário logado ou null;
                            logado = telaLogin(conn);

                            //Se o login der certo, ele vai usar o login e a conexão para chamar a tela inicial
                            if (logado != null) {
                                telaHome(logado, conn);
                            }
                        }
                        case 2 -> telaCadastro(); //metodo para cadastro de usuário
                        case 0 -> {
                            System.out.println("Saindo...");
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }

                  //tratamento de erro para entradas inválidas
                } catch (InputMismatchException e) {
                    System.out.println("ERRO! Entrada inválida, insira um número.");
                    return;
                }
            }
        //tratamento de erro caso a conexão seja perdida, driver não esteja sendo usado corretamente, etc.
        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
            e.printStackTrace();
        } finally { //fecha a conexão com o banco de dados
            try {
                closeConnection(conn);
            } catch (Exception e) {
                System.out.println("ERRO! Não foi possível fechar a conexão com o banco de dados!");
                e.printStackTrace();
            }
        }
    }
}
