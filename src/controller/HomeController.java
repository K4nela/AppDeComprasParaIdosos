package controller;

import dao.UsuarioDAO;
import model.usuario;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import static view.Menus.*;

public final class  HomeController {

    public static void telaHome(usuario u,UsuarioDAO opcaoDao, Connection conn) throws Exception {
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
                    case "idoso":

                        while (true){
                            try {
                                menuHomeIdoso();
                                opcao = scn.nextInt();

                                switch (opcao) {
                                    case 1 -> UsuarioController.verPerfil(u, conn);
                                    case 2 -> System.out.println("função ver lista de desejos sendo desenvolvida..."); // implementar
                                    case 3 -> System.out.println("função criar lista de desejos sendo desenvolvida..."); // implementar
                                    case 4 -> System.out.println("função ver familiares sendo desenvolvida..."); // implementar
                                    case 5 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
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

                    case "familiar":

                        while(true) {

                            try{
                                menuHomeFamiliar();
                                opcao = scn.nextInt();

                                switch (opcao) {
                                    case 1 -> UsuarioController.verPerfil(u, conn);
                                    case 2 -> System.out.println("função ver lista de desejos sendo desenvolvida..."); // implementar
                                    case 3 -> System.out.println("função ver idosos sendo desenvolvida..."); // implementar
                                    case 4 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
                                    case 0 -> {
                                        System.out.println("Saindo...");
                                        return;
                                    }

                                    default -> System.out.println("Opção inválida!");
                                }
                            }catch (InputMismatchException e) {
                                System.out.println("ERRO! Digite apenas números");
                                scn.nextLine();
                            }
                        }

                    case "administrador":

                        while(true) {
                            try{
                                menuHomeAdmin();
                                opcao = scn.nextInt();
                                scn.nextLine();

                                switch (opcao) {
                                    case 1 -> UsuarioController.verPerfil(u, conn);
                                    case 2 -> System.out.println("função gerenciar usuários sendo desenvolvida..."); // implementar
                                    case 3 -> UsuarioController.admVerUsuarios(conn);
                                    case 4 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
                                    case 0 -> {
                                        System.out.println("Saindo...");
                                        return;
                                    }
                                    default -> System.out.println("Opção inválida!");
                                }
                            }catch (InputMismatchException e) {
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
