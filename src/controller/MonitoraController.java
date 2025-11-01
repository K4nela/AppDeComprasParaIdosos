package controller;

import dao.MonitoraDAO;
import model.usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public final class MonitoraController extends MonitoraDAO {

    public MonitoraController(Connection conn) {
        super(conn);
    }

    // Lista os familiares associados ao idoso atual
    static void listarFamiliares(usuario u, Connection conn) {
        try {
            MonitoraDAO dao = new MonitoraDAO(conn);
            List<usuario> familia = dao.getByFm(u.getId());

            if (familia.isEmpty()) {
                System.out.println("ERRO! Nenhum familiar associado a este idoso.");
                return;
            }

            System.out.println("----- Familiares -----");
            for (usuario f : familia) {
                System.out.println("nome: " + f.getNome() +
                        "\ndata de nascimento: " + f.getDataNasc() +
                        "\nemail: " + f.getE_mail() +
                        "\nendereço: " + f.getEndereco() +
                        "\ntelefone: " + f.getTelefone());
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível listar familiares: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lista os idosos associados ao familiar atual e oferece vincular novo idoso
    static void listarIdosos(usuario u, Connection conn) {
        Scanner scn = new Scanner(System.in);

        try {
            MonitoraDAO dao = new MonitoraDAO(conn);
            List<usuario> idosos = dao.getById(u.getId());

            if (idosos.isEmpty()) {
                System.out.println("ERRO! Nenhum idoso associado a este familiar.");

            } else {
                System.out.println("----- Idosos -----");
                for (usuario idoso : idosos) {
                    System.out.println("nome: " + idoso.getNome() +
                            "\ndata de nascimento: " + idoso.getDataNasc() +
                            "\nemail: " + idoso.getE_mail() +
                            "\nendereço: " + idoso.getEndereco() +
                            "\ntelefone: " + idoso.getTelefone());
                    System.out.println("----------------------");
                }
            }


            System.out.println("1 - Vincular mais idosos");
            String opcao = scn.nextLine();

            if (opcao.equalsIgnoreCase("1")) {
                MonitoraDAO monitoradao = new MonitoraDAO(conn);

                System.out.print("Digite o email do idoso a vincular a sua conta: ");
                String email = scn.nextLine();
                int id_idoso = getIdByEmail(conn, email);

                monitoradao.vincular(u.getId(), id_idoso);
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Operação cancelada.");

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível listar ou vincular idosos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //associa idoso ao familiar acessando o id atraves de usuarios instanciados
    public static void associarFamiliarEIdoso(Connection conn, usuario familiar, usuario idoso) throws Exception {
        dao.MonitoraDAO monitoraDAO = new dao.MonitoraDAO(conn);

        int idFamiliarPk = (familiar instanceof model.familiar) ? ((model.familiar) familiar).getId_familiar() : familiar.getId();
        int idIdosoPk = (idoso instanceof model.idoso) ? ((model.idoso) idoso).getId_idoso() : idoso.getId();
        monitoraDAO.vincular(idFamiliarPk, idIdosoPk);

        System.out.println("Associação entre familiar e idoso criada com sucesso!");
    }
}
