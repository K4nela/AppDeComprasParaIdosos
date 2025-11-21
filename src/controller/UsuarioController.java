package controller;

import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static view.Listas.*;
import static view.Menus.*;

/*
gerenciar usuários do sistema (função para Administradores, CRUD completo)
 */
public class UsuarioController {
    static Scanner scn = new Scanner(System.in);
    static int opcao = -1;


    /*
    função para ver usuários do sistema (função para Administradores)
     */
    public static final void VerUsuarios(Connection conn) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

        while (true) {
            try {
                menuVerUsuarios();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> exibirListaUsuarios(usuarioDAO);//opção para exibir todos os usuários (metodo da view -> Listas)
                    case 2 -> { //opção para exibir o usuário por id (metodo da view -> Listas)
                        System.out.print("Digite o ID do usuário: ");
                        int id = scn.nextInt();

                        exibirUsuarioPorId(usuarioDAO, id);
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }

                //erro, trata entradas que não são numéricas
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        }

    }

    /*
    Tela principal de gerenciamentp de usuário (função para Administradores)
     */
    public static final void gerenciarUsuarios(Connection conn) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn); //dao para operações com usuários

        while (true) {
            try {
                menuGerenciarUsuarios();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> VerUsuarios(conn); //opção para listar todos os usuários ou por ID
                    case 2 -> { //opçãp para atualizar o usuário por ID
                        System.out.println("Digite [0] para voltar");
                        exibirListaUsuarios(usuarioDAO);

                        System.out.println("Digite o ID do usuário a ser atualizado: ");
                        int id = scn.nextInt();

                        if (id == 0) {
                            System.out.println("Voltando...");
                            break;
                        }

                        usuarioDAO.update(id); //chama atualização por id de usuário
                    }

                    case 3 -> { //opção para deletar usuário
                        System.out.println("Digite [0] para voltar");
                        exibirListaUsuarios(usuarioDAO);

                        System.out.print("Digite o ID do usuário a ser deletado: ");
                        int id = scn.nextInt();

                        if (id == 0) {
                            System.out.println("Voltando...");
                            break;
                        }

                        usuarioDAO.delete(id); //função para confirmar a exclusão do usuário
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
                //erro, trata entradas que não são numéricas
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }
}
