package controller;

import dao.*;


import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static view.Menus.menuTipoStatus;
import static view.ToStrings.exibirListasDeDesejos;
import static view.Menus.menuOpcaoLista;

public class ListaDeDesejosController {
    final ListaDeDesejosDAO listaDao;

    public ListaDeDesejosController(Connection conn) {
        this.listaDao = new ListaDeDesejosDAO(conn);
    }

    public static void telaListaDeDesejos(usuario u, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao =  new itemDAO(conn);
        listaDeDesejos lista = null;

        int opcao = -1;

        while (true) {
            int id_idoso = u.getId();
            exibirListasDeDesejos(id_idoso, usuarioDao, itemDao, listaDao);

            menuOpcaoLista();
            opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite [0] para voltar");
                    System.out.println("---------CriandoLista---------");

                    System.out.printf("Nome da lista: ");
                    String nomeLista = scn.nextLine();

                    if (nomeLista.equals("0")) {
                        System.out.println("Voltando...");
                        return;
                    }

                    System.out.printf("Descrição da lista: ");
                    String descricao = scn.nextLine();

                    if (descricao.equals("0")) {
                        System.out.println("Voltando...");
                        return;
                    }
                    int idIdoso = usuarioDao.getIdIdosoByUsuario(id_idoso);
                    lista = new listaDeDesejos(idIdoso, nomeLista, descricao);
                    listaDao.save(lista);
                }
                case 2 -> listaDao.getByIdIdoso(usuarioDao.getIdIdosoByUsuario(id_idoso));

                case 3 -> {
                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite o id da lista que deseja editar: ");
                    int id = scn.nextInt();
                    scn.nextLine();

                    if (id == 0) {
                        System.out.println("Voltando...");
                        return;
                    }

                    listaDao.update(id);
                }

                case 4 -> {
                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite o id da lista para acessar seus itens: ");
                    int id = scn.nextInt();
                    scn.nextLine();

                    if (id == 0) {
                        System.out.println("Voltando...");
                        return;
                    }

                    ItemListaController.telaItens(id, conn);
                }

                case 0 -> {
                    return;
                }
            }
        }
    }

    public static void telaListasDeDesejosPorFamiliar(usuario u, Connection conn) throws SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        HistoricoDAO historicoDao = new HistoricoDAO(conn);
        MonitoraDAO monitoraDAO = new MonitoraDAO(conn);
        Scanner scn = new Scanner(System.in);
        idoso uIdoso = null;

        // Pega o familiar logado
        familiar fam = usuarioDao.getFamiliarByEmail(u.getE_mail());

        // Pega os idosos vinculados
        List<usuario> idosos = monitoraDAO.getById(fam.getId());

        // Itera pelos idosos
        List<Integer> idsIdosos = monitoraDAO.getIdososByFamiliar(u.getId());

        if (idsIdosos.isEmpty()) {
            System.out.println("ERRO! Nenhum idoso vinculado ao familiar " + fam.getNome());
        } else {
            for (int idIdoso : idsIdosos) {
                exibirListasDeDesejos(idIdoso, usuarioDao, itemDao, listaDao);
            }
        }


        // Pergunta se quer alterar status de algum item
        System.out.println("\nDeseja alterar o status de algum item? [S/N]");
        String opcao = scn.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            System.out.print("Digite o ID do item: ");
            int idItem = scn.nextInt();
            scn.nextLine();

            historicoDao.update(idItem);
        }
    }
}
