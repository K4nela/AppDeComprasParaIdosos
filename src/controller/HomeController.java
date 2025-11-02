package controller;

import model.usuario;

import static controller.ListaDeDesejosController.telaListaDeDesejos;
import static controller.MonitoraController.listarFamiliares;
import static controller.MonitoraController.listarIdosos;
import static controller.UsuarioController.*;
import static controller.LoginController.*;
import static view.Menus.*;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public final class HomeController {

    /*
	Desenvolvendo a tela Home (tela inicial) do sistema.
	Onde o usuário poderá acessar as funcionalidades de acordo com seu tipo (idoso, familiar ou administrador)
	 */
    public static void telaHome(usuario u, Connection conn) throws Exception {
        String sql = "SELECT tipo FROM usuario WHERE id = ?";
        Scanner scn = new Scanner(System.in);
        int opcao = 0;
        int id = u.getId();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");

                switch (tipo) {
                    case "idoso": //Menu Home adaptado para usuários do tipo Idoso.

                        while (true) {
                            try {
                                menuHomeIdoso();
                                opcao = scn.nextInt();

                                switch (opcao) {
                                    case 1 -> verPerfil(u, conn);
                                    case 2 -> telaListaDeDesejos(u,conn); // implementar
                                    case 3 -> listarFamiliares(u, conn);
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

                    case "familiar": //Menu Home adaptado para usuários do tipo Familiar.

                        while (true) {

                            try {
                                menuHomeFamiliar();
                                opcao = scn.nextInt();

                                switch (opcao) {
                                    case 1 -> verPerfil(u, conn);
                                    case 2 -> System.out.println("função ver lista de desejos sendo desenvolvida..."); // implementar
                                    case 3 -> listarIdosos(u, conn);
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

                    case "administrador": //Menu Home adaptado para usuários do tipo Administrador.

                        while (true) {
                            try {
                                menuHomeAdmin();
                                opcao = scn.nextInt();
                                scn.nextLine();

                                switch (opcao) {
                                    case 1 -> {
                                        u = verPerfil(u, conn);
                                        if (u == null) return;
                                    }
                                    case 2 -> gerenciarUsuarios(conn);
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

                    default:
                        System.out.println("Tipo de usuário inválido!");
                }
            } else {
                System.out.println("Usuário não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível acessar a tela inicial!");
        }
    }
}


