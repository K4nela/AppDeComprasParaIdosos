package controller;

import dao.ListaDeDesejosDAO;
import dao.UsuarioDAO;
import dao.itemDAO;
import model.item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static view.Menus.menuItem;
import static view.Listas.exibirListaDeDesejosById;

public class ItemListaController {

    public static void telaItens(int idlista, Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        item item = null;

        int opcao = -1;

        while (true) {
            menuItem();
            opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1 -> {
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

                case 2 -> {
                    try{
                        exibirListaDeDesejosById(idlista, listaDao, itemDao);
                        System.out.printf("Digite o id do item que deseja editar: ");
                        int idItem = scn.nextInt();
                        scn.nextLine();

                        itemDao.update(idItem);

                    }catch (SQLException e){
                        System.out.printf("ERRO! Não foi possível deletar este item!");
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    try{
                        exibirListaDeDesejosById(idlista, listaDao, itemDao);
                        System.out.printf("Digite o id do item que deseja excluir: ");
                        int idItem = scn.nextInt();
                        scn.nextLine();

                        itemDao.delete(idItem);

                    }catch (SQLException e){
                        System.out.printf("ERRO! Não foi possível deletar este item!");
                        e.printStackTrace();
                    }
                }

                case 0 -> {
                    System.out.println("Voltando...");
                    return;
                }

            }
        }
    }
}

