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

/*
Controla o CRUD de itens dentro de uma lista de desejos.
Usado por idosos (criar/editar) e familiares (acompanhar status).
*/
public class ItemListaController {
    static Scanner scn = new Scanner(System.in);

    /*
    Edita um item existente (nome, descrição, quantidade, loja, link).
     */
    public static void editarItem(int idlista, itemDAO itemDao, ListaDeDesejosDAO listaDao) {
        try {
            exibirListaDeDesejosById(idlista, listaDao, itemDao);//exibe lista e seuse itens para lista para o usuário logado escolher (metodo da view -> Listas)
            System.out.printf("Digite o id do item que deseja editar: ");
            int idItem = scn.nextInt();
            scn.nextLine();

            itemDao.update(idItem);//chama menu de atualização do DAO

        } catch (SQLException e) {
            System.out.printf("ERRO! Não foi possível editar este item!");
            e.printStackTrace();
        }
    }

    /*
    Cadastra um novo item e cria histórico PENDENTE automaticamente
     */
    public static void cadastrarItens(int idlista, itemDAO itemDao) throws SQLException {
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

        item i = new item(idlista, nomeItem, descricaoItem, quant, nomeLoja, link);// Cria objeto item
        itemDao.save(i); // Salva no banco → retorna ID gerado
    }

    /*
    Deleta um item com confirmação Y/N (dentro do DAO).
     */
    public static void deletarItem(int idlista, itemDAO itemDao, ListaDeDesejosDAO listaDao) {

        try {
            while (true) {

                try {
                    exibirListaDeDesejosById(idlista, listaDao, itemDao);//exibe lista e seuse itens para lista para o usuário logado escolher (metodo da view -> Listas)
                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite o id do item que deseja excluir: ");
                    int idItem = scn.nextInt();
                    scn.nextLine();

                    if(idItem == 0){
                        System.out.println("Voltando...");
                        return;
                    }

                    itemDao.delete(idItem);// metodo de confirmação Y/N dentro do DAO

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

    /*
    Tela principal de gerenciamento de itens.
     */
    public static void telaItens(int idlista, Connection conn) throws SQLException {
        //DAOs instanciadas para buscas e acesso de metodos
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
                        cadastrarItens(idlista, itemDao); // Cadastra item e cria histórico (metodo de ListaItemController)
                        return;
                    }
                    case 2 -> editarItem(idlista, itemDao, listaDao); // Edita item (metodo de ListaItemController)
                    case 3 -> deletarItem(idlista, itemDao, listaDao); // Deleta item (metodo de ListaItemController)
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

