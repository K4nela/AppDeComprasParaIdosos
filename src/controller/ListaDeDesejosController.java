package controller;

import dao.*;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static controller.ItemListaController.telaItens;
import static view.Listas.*;
import static view.Menus.menuOpcaoLista;

public class ListaDeDesejosController {
    final ListaDeDesejosDAO listaDao;

    public ListaDeDesejosController(Connection conn) {
        this.listaDao = new ListaDeDesejosDAO(conn);
    }

    public static void criarLista(int id, Scanner scn, UsuarioDAO usuarioDao, listaDeDesejos lista, ListaDeDesejosDAO listaDao) throws SQLException {
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
        int ididoso = usuarioDao.getIdIdosoByUsuario(id);
        lista = new listaDeDesejos(ididoso, nomeLista, descricao);
        listaDao.save(lista);
    }

    public static void editarLista(int id_idoso, UsuarioDAO usuarioDao, itemDAO itemDao, Scanner scn, ListaDeDesejosDAO listaDao) throws SQLException {

        while (true) {
            try {
                exibirCabecalhoLista(id_idoso, usuarioDao, itemDao, listaDao);

                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite o id da lista que deseja editar: ");
                int id = scn.nextInt();
                scn.nextLine();

                if (id == 0) {
                    System.out.println("Voltando...");
                    return;
                }

                listaDao.update(id);

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    public static void acessarLista(Connection conn, Scanner scn, ListaDeDesejosDAO listaDao, itemDAO itemDao) throws SQLException {
        while(true) {
            try {
                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite o id da lista para acessar: ");
                int id = scn.nextInt();
                scn.nextLine();

                if (id == 0) {
                    System.out.println("Voltando...");
                    return;
                }
                    exibirListaDeDesejosById(id, listaDao, itemDao);
                    telaItens(id, conn);

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    public static void telaListaDeDesejos(usuario u, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        listaDeDesejos lista = null;

        int opcao = -1;

        while (true) {
            try {
                int id_idoso = u.getId();
                exibirCabecalhoLista(id_idoso, usuarioDao, itemDao, listaDao);

                menuOpcaoLista();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> criarLista(id_idoso, scn, usuarioDao, lista, listaDao);
                    case 2 -> acessarLista(conn, scn, listaDao, itemDao);
                    case 3 -> editarLista(id_idoso, usuarioDao, itemDao,scn, listaDao);
                    case 0 -> {
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    public static void verListasDeIdosos(usuario u, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        MonitoraDAO monitoraDAO = new MonitoraDAO(conn);
        ListaDeDesejosDAO listaDAO = new ListaDeDesejosDAO(conn);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        HistoricoDAO historicoDao = new HistoricoDAO(conn);
        int opcao = -1;


        do {
            try {
                int idFamiliar = usuarioDao.getIdFmById(u.getId());

                List<idoso> idososIds = monitoraDAO.getIdososByFamiliar(idFamiliar);

                if (idososIds.isEmpty()) {
                    System.out.println("Você ainda não monitora nenhum idoso");
                    return;
                }

                for (idoso idosoId : idososIds) {
                    usuario idoso = usuarioDao.getUsByIds(idosoId.getId_idoso());
                    List<listaDeDesejos> listas = listaDAO.getByIdIdoso(idosoId.getId_idoso());
                    telaListaDosIsosos(listas, idoso);
                }

                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite um ID para acessar a lista de desejos: ");
                opcao = scn.nextInt();
                scn.nextLine();

                while (true) {
                    exibirListaDeDesejosById(opcao, listaDAO, itemDao);

                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite um ID para atualizar o status de um item: ");
                    opcao = scn.nextInt();
                    scn.nextLine();

                    if (opcao == 0) {
                        System.out.println("Voltando...");
                        return;
                    }

                    historicoDao.update(opcao);
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        } while (opcao != 0);
    }
}
