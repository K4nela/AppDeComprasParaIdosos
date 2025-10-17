package view;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.*;
import java.util.Scanner;
import static view.Menus.*;

public class TelaHome {

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
                        do{
                            menuHomeIdoso();
                            opcao = scn.nextInt();

                            switch (opcao) {
                                case 1 -> System.out.println(opcaoDao.getById(id));
                                case 2 -> System.out.println("função ver lista de desejos sendo desenvolvida..."); // implementar
                                case 3 -> System.out.println("função criar lista de desejos sendo desenvolvida..."); // implementar
                                case 4 -> System.out.println("função ver familiares sendo desenvolvida..."); // implementar
                                case 5 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
                                case 0 -> System.out.println("Saindo...");
                                default -> System.out.println("Opção inválida!");
                            }
                        }while (opcao != 0);

                        break;

                    case "familiar":

                        do {
                            menuHomeFamiliar();
                            opcao = scn.nextInt();

                            switch (opcao) {
                                case 1 -> System.out.println(opcaoDao.getById(id));
                                case 2 -> System.out.println("função ver lista de desejos sendo desenvolvida..."); // implementar
                                case 3 -> System.out.println("função ver idosos sendo desenvolvida..."); // implementar
                                case 4 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
                                case 0 -> System.out.println("Saindo...");
                                default -> System.out.println("Opção inválida!");
                            }
                        }while (opcao != 0);
                        break;

                    case "administrador":

                        do {
                            menuHomeAdmin();
                            opcao = scn.nextInt();
                            scn.nextLine();

                            switch (opcao) {
                                case 1 -> System.out.println(opcaoDao.getById(id));
                                case 2 -> System.out.println("função gerenciar usuários sendo desenvolvida..."); // implementar
                                case 3 -> System.out.println(opcaoDao.get());
                                case 4 -> System.out.println("função para opções sendo desenvolvida..."); // implementar
                                case 0 -> System.out.println("Saindo...");
                                default -> System.out.println("Opção inválida!");
                            }
                        }while (opcao != 0);
                        break;

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
