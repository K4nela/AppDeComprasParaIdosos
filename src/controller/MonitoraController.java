package controller;

import dao.MonitoraDAO;
import dao.UsuarioDAO;
import model.familiar;
import model.idoso;
import model.usuario;

import java.sql.Connection;
import java.sql.SQLException;
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

    // Lista os idosos associados ao familiar atual e oferece opcao de vincular novo idoso
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
                    System.out.println( "nome: " + idoso.getNome() +
                                        "\ndata de nascimento: " + idoso.getDataNasc() +
                                        "\nemail: " + idoso.getE_mail() +
                                        "\nendereço: " + idoso.getEndereco() +
                                        "\ntelefone: " + idoso.getTelefone());
                    System.out.println("----------------------");
                }
            }

            associar(u, conn);

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Operação cancelada.");

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível listar ou vincular idosos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //associa idoso ao familiar usando metodo associarFamiliarEIdoso
    public static void associar(usuario u, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);

        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        String u_email = u.getE_mail();
        familiar f = usuarioDao.getFamiliarByEmail(u_email);
        usuario idosoEncontrado = null;
        String email;

        System.out.println("Digite [0] para sair;");
        System.out.println("1 - Vincular mais idosos");
        System.out.printf("Escolha uma opção: ");

        int opcao = scn.nextInt();
        scn.nextLine();

        if (opcao == 0) {
            return;
        }

        switch (opcao) {
            case 1 -> {
                while (true) {
                    System.out.println("Digite [0] para sair;");
                    System.out.print("Digite o email do idoso a vincular a sua conta: ");
                    email = scn.nextLine();
                    idosoEncontrado = usuarioDao.getIdosoByEmail(email);

                    if (email.equals("0")) {
                        return;
                    }

                    if (idosoEncontrado == null) {
                        System.out.println("ERRO! Familiar não encontrado. Cadastre o familiar antes ou informe um email válido.");
                    }
                    try {
                        // chama o metodo que faz a associação
                        associarFamiliarEIdoso(conn, f, idosoEncontrado);

                    } catch (Exception e) {
                        System.out.println("ERRO! Não foi associar idoso ao familiar.");
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    //associa idoso ao familiar acessando o id atraves de usuarios instanciados
    public static void associarFamiliarEIdoso(Connection conn, usuario familiar, usuario idoso) throws Exception {
        dao.MonitoraDAO monitoraDAO = new dao.MonitoraDAO(conn);

        // pega os ids específicos, não o id genérico
        int idFamiliarPk = (familiar instanceof model.familiar) ? ((model.familiar) familiar).getId_familiar() : familiar.getId();
        int idIdosoPk = (idoso instanceof model.idoso) ? ((model.idoso) idoso).getId_idoso() : idoso.getId();

        // verifica se os ids são válidos
        if (idFamiliarPk == 0 || idIdosoPk == 0) {
            throw new Exception("Erro: id do familiar ou id do idoso inválido!");
        }
        // faz a associação
        monitoraDAO.vincular(idFamiliarPk, idIdosoPk);
    }

}
