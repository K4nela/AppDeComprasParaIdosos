package app;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;
import java.sql.Connection;
import java.util.Scanner;

import static view.Cadastro.telaCadastro;
import static view.Menus.*;
import static view.TelaLogin.telaLogin;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static int opcao = 0;

    public static void main(String[] args){
//        int id;
          int opcao;

        try{
            Connection conn = Conexao.createConnectionToMySQL();//Conecta no banco de dados
            UsuarioDAO UsuarioDao = new UsuarioDAO(conn);//Instancia o DAO

            menuLogin();
            do{
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> telaLogin(opcao);
                    case 2 -> telaCadastro();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }

            }while (opcao!=0);

        }catch (Exception e){
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
        }
    }
}
