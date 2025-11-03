package controller;

import dao.UsuarioDAO;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.Menus.*;
import static view.Listas.exibirListaUsuarios;
import static view.Listas.exibirUsuarioPorId;

public class UsuarioController {

    //Desenvolvendo função para gerenciar usuários do sistema (função para Administradores)
    public static final void gerenciarUsuarios(Connection conn) {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        int opcao = -1;

        while (true) {
            try {
                menuGerenciarUsuarios();
                opcao = scn.nextInt();

                switch (opcao) {
                    case 1 -> VerUsuarios(conn);
                    case 2 -> {
                        System.out.println("Digite [0] para voltar");
                        System.out.println(usuarioDAO.get());

                        System.out.println("Digite o ID do usuário a ser atualizado: ");
                        int id = scn.nextInt();
                        usuarioDAO.update(id);


                        if (id == 0) {
                            System.out.println("Voltando...");
                            return;
                        }
                    }
                    case 3 -> {
                        System.out.println("Digite [0] para voltar");
                        System.out.println(usuarioDAO.get());

                        System.out.print("Digite o ID do usuário a ser deletado: ");
                        int id = scn.nextInt();
                        usuarioDAO.delete(id);

                        if (id == 0) {
                            System.out.println("Voltando...");
                            return;
                        }
                    }
                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        }

    }

    //Desenvolvendo função para ver usuários do sistema (função para Administradores)
    public static final void VerUsuarios(Connection conn) {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        int opcao = -1;

        while (true) {
            try {
                menuVerUsuarios();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> exibirListaUsuarios(usuarioDAO);
                    case 2 -> {
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

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        }

    }
}
