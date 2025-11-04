package controller;

import dao.UsuarioDAO;
import model.usuario;

import static controller.ListaDeDesejosController.telaListaDeDesejos;
import static controller.ListaDeDesejosController.verListasDeIdosos;
import static controller.MonitoraController.listarFamiliares;
import static controller.MonitoraController.listarIdosos;
import static controller.UsuarioController.*;
import static controller.LoginController.*;
import static view.Menus.*;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class HomeController {
    static Scanner scn = new Scanner(System.in);

    /*
      Controla a tela inicial (home) da aplicação após login.
      Exibe o menu personalizado conforme o tipo de usuário (idoso, familiar ou administrador).
      O Controller não faz consultas diretas ao banco — ele chama o DAO responsável.
     */
    public static void telaHome(usuario u, Connection conn) throws Exception {
        // Instancia o DAO para acessar os dados do usuário
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

        // Busca o tipo do usuário (idoso / familiar / administrador) no banco
        String tipo = usuarioDAO.getTipoById(u.getId());

        if (tipo == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        /*
         SELECIONA O MENU DE ACORDO COM O TIPO DE USUÁRIO
         */
        switch (tipo) {
            case "idoso" -> menuIdoso(u, conn);            // Menu adaptado para usuários idosos
            case "familiar" -> menuFamiliar(u, conn);      // Menu adaptado para usuários familiares
            case "administrador" -> menuAdmin(u, conn);    // Menu adaptado para usuários administradores
            default -> System.out.println("Tipo de usuário inválido!");
        }
    }

    /*
      Menu Home adaptado para usuários do tipo Idoso.
      Permite acessar o perfil, listas de desejos e familiares associados.
     */
    private static void menuIdoso(usuario u, Connection conn) throws Exception {
        while (true) {
            try {
                menuHomeIdoso(); // Exibe o menu do idoso
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> verPerfil(u, conn);          // Acessa o perfil e suas configurações
                    case 2 -> telaListaDeDesejos(u, conn); // Acessa a lista de desejos
                    case 3 -> listarFamiliares(u, conn);   // Lista os familiares associados
                    case 0 -> {
                        System.out.println("Voltando..."); // Volta para a tela de login
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                // Trata entradas que não são numéricas
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    /*
      Menu Home adaptado para usuários do tipo Familiar.
      Permite acessar o perfil, visualizar listas dos idosos e listar idosos monitorados.
     */
    private static void menuFamiliar(usuario u, Connection conn) throws Exception {
        while (true) {
            try {
                menuHomeFamiliar(); // Exibe o menu do familiar
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> verPerfil(u, conn);           // Acessa o perfil e suas configurações
                    case 2 -> verListasDeIdosos(u, conn);   // Acessa as listas de desejos dos idosos associados
                    case 3 -> listarIdosos(u, conn);        // Lista os idosos monitorados
                    case 0 -> {
                        System.out.println("Voltando...");  // Volta para a tela de login
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                // Trata entradas que não são numéricas
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    /*
      Menu Home adaptado para usuários do tipo Administrador.
      Permite acessar o perfil e gerenciar todos os usuários cadastrados no sistema.
     */
    private static void menuAdmin(usuario u, Connection conn) throws Exception {
        while (true) {
            try {
                menuHomeAdmin(); // Exibe o menu do administrador
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> verPerfil(u, conn);         // Acessa o perfil e suas configurações
                    case 2 -> gerenciarUsuarios(conn);    // Gerencia os dados de todos os usuários
                    case 0 -> {
                        System.out.println("Voltando..."); // Volta para a tela de login
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                // Trata entradas que não são numéricas
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }
}
