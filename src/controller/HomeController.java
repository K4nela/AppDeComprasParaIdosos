package controller;

import model.usuario;

import static controller.ListaDeDesejosController.telaListaDeDesejos;
import static controller.ListaDeDesejosController.verListasDeIdosos;
import static controller.MonitoraController.listarFamiliares;
import static controller.MonitoraController.listarIdosos;
import static controller.UsuarioController.*;
import static controller.LoginController.*;
import static view.Menus.*;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public final class HomeController {
    static Scanner scn = new Scanner(System.in);
    static int opcao = -1;

    /*
      Controla a tela inicial (home) da aplicação após login.
      Exibe menu personalizado conforme o tipo de usuário (idoso, familiar, admin)
     */
    public static void telaHome(usuario u, Connection conn) throws Exception {
        // SQL para buscar o tipo do usuário (idoso/familiar/administrador)
        String sql = "SELECT tipo FROM usuario WHERE id = ?";
        int opcao = 0;
        int id = u.getId(); //pega o id do usuário logado

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); //define a variável que será usada na query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");//tipo retonado pelo banco após a seleção

                /*
                SELECIONA O MENU DE ACORDO COM O TIPO DE USUÁRIO
                 */
                switch (tipo) {
                    case "idoso" -> { //Menu Home adaptado para usuários do tipo Idoso.

                        while (true) {
                            try {
                                menuHomeIdoso();
                                opcao = scn.nextInt();
                                scn.nextLine();

                                switch (opcao) {
                                    case 1 -> verPerfil(u, conn);//acessa o perfil e suas configurações (metodo da LoginController)
                                    case 2 -> telaListaDeDesejos(u,conn); //acessa a lista de desejos e suas opções (metodo da ListaDeDesejosController)
                                    case 3 -> listarFamiliares(u, conn); //lista os familiares associados (metodo da MonitoraController)
                                    case 0 -> {
                                        System.out.println("Voltando..."); //volta para a tela de login
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

                    case "familiar" -> { //Menu Home adaptado para usuários do tipo Familiar.

                        while (true) {

                            try {
                                menuHomeFamiliar();
                                opcao = scn.nextInt();
                                scn.nextLine();

                                switch (opcao) {
                                    case 1 -> verPerfil(u, conn);//acessa o perfil e suas configurações (metodo da LoginController)
                                    case 2 -> verListasDeIdosos(u, conn); //acessa a lista de desejos dos idosos associados (metodo da ListaDeDesejosController)
                                    case 3 -> listarIdosos(u, conn); //lista os usuários idosos associados ao usuário familiar (metodo da MonitoraController)
                                    case 0 -> {
                                        System.out.println("Voltando..."); //volta para a tela de login
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

                    case "administrador" -> { //Menu Home adaptado para usuários do tipo Administrador.

                        while (true) {
                            try {
                                menuHomeAdmin();
                                opcao = scn.nextInt();
                                scn.nextLine();

                                switch (opcao) {
                                    case 1 ->verPerfil(u, conn); //acessa o perfil e suas configurações (metodo da LoginController)
                                    case 2 -> gerenciarUsuarios(conn); //acessa a lista de todos os usuários cadastrados no banco de dados, gerencia os dados dos usuários (metodo da UsuarioController)
                                    case 0 -> {
                                        System.out.println("Voltando..."); //volta para a tela de login
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
                    default -> System.out.println("Tipo de usuário inválido!");
                }
            } else {
                System.out.println("Usuário não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível acessar a tela inicial!");
            e.printStackTrace();
        }
    }
}


