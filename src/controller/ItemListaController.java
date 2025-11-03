package controller;

import dao.ListaDeDesejosDAO;
import dao.UsuarioDAO;
import dao.itemDAO;
import model.item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.Menus.menuItem;
import static view.Listas.exibirListaDeDesejosById;

public class ItemListaController {

    public static void editarItem(int idlista, Scanner scn, itemDAO itemDao, ListaDeDesejosDAO listaDao) {
        try {
            exibirListaDeDesejosById(idlista, listaDao, itemDao);
            System.out.printf("Digite o id do item que deseja editar: ");
            int idItem = scn.nextInt();
            scn.nextLine();

            itemDao.update(idItem);

        } catch (SQLException e) {
            System.out.printf("ERRO! Não foi possível editar este item!");
            e.printStackTrace();
        }
    }

    public static void cadastrarItens(int idlista, itemDAO itemDao, Scanner scn) throws SQLException {
        System.out.println("Digite [0] para voltar");
        System.out.println("-----------CriandoItem-----------");
        System.out.printf("Nome: ");
        String nomeItem = scn.nextLine();

        if (nomeItem.equals("0")) {
            System.out.println("Voltando...");
            return;
        }

        System.out.printf("Descricao: ");
        String descricaoItem = scn.nextLine();

        if (descricaoItem.equals("0")) {
            System.out.println("Voltando...");
            return;
        }

        System.out.printf("Quantidade: ");
        int quant = scn.nextInt();
        scn.nextLine();

        if (quant == 0) {
            System.out.println("Voltando...");
            return;
        }

        System.out.printf("nome da loja: ");
        String nomeLoja = scn.nextLine();

        if (nomeLoja.equals("0")) {
            System.out.println("Voltando...");
            return;
        }

        System.out.printf("link: ");
        String link = scn.nextLine();

        if (link.equals("0")) {
            System.out.println("Voltando...");
            return;
        }

        item i = new item(idlista, nomeItem, descricaoItem, quant, nomeLoja, link);
        itemDao.save(i);
    }

    public static void deletarItem(int idlista, Scanner scn, itemDAO itemDao, ListaDeDesejosDAO listaDao) {

        try {
            while (true) {

                try {
                    exibirListaDeDesejosById(idlista, listaDao, itemDao);
                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite o id do item que deseja excluir: ");
                    int idItem = scn.nextInt();
                    scn.nextLine();

                    if(idItem == 0){
                        System.out.println("Voltando...");
                        return;
                    }

                    itemDao.delete(idItem);

                } catch (InputMismatchException e) {
                    System.out.println("ERRO! Digite apenas números");
                    scn.nextLine();
                }
            }
        } catch (SQLException e) {
            System.out.printf("ERRO! Não foi possível deletar este item!");
            e.printStackTrace();
        }
    }

    public static void telaItens(int idlista, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        item item = null;

        int opcao = -1;

        while (true) {

            try {
                menuItem();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> {
                        cadastrarItens(idlista, itemDao, scn);
                        return;
                    }
                    case 2 -> editarItem(idlista, scn, itemDao, listaDao);
                    case 3 -> deletarItem(idlista, scn, itemDao, listaDao);
                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }
}

