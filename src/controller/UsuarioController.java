package controller;

import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.Menus.menuListarUsuarios;
import static view.Menus.menuPerfil;

public class UsuarioController {

    public static void verPerfil(usuario u, Connection conn){
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        int id = u.getId();
        int opcao = -1;

        while (true){
            try{
                menuPerfil();
                opcao = scn.nextInt();

                switch (opcao){
                    case 1 -> System.out.println(usuarioDAO.getById(id));
                    case 2 -> usuarioDAO.update(id);
                    case 0 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }

            }catch (InputMismatchException e){
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        }
    }

    public static final void admVerUsuarios(Connection conn){
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        int opcao = -1;

        while (true){
            try{
                menuListarUsuarios();
                opcao = scn.nextInt();

                switch (opcao){
                    case 1 -> System.out.println(usuarioDAO.get());
                    case 2 -> {
                        System.out.print("Digite o ID do usuário: ");
                        int id = scn.nextInt();
                        System.out.println(usuarioDAO.getById(id));
                    }
                    case 0 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }

            }catch (InputMismatchException e){
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        }

    }
}
