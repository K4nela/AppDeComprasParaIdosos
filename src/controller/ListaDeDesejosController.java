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


/*
Controla as listas de desejos dos idosos.
Permite criar, editar, deletar e visualizar listas.
*/
public class ListaDeDesejosController {
    static int opcao = -1;
    static ListaDeDesejosDAO listaDao;
    static Scanner scn = new Scanner(System.in);

    public ListaDeDesejosController(Connection conn) {
        listaDao = new ListaDeDesejosDAO(conn);
    }

    public static int erroLista(ListaDeDesejosDAO listaDao, int id) throws SQLException {
        listaDeDesejos lista = listaDao.getById(id);

        while (lista == null) {
            System.out.printf("Digite um id válido: ");
            id = scn.nextInt();
            scn.nextLine();
            lista = listaDao.getById(id);

            if(id == 0){
                return 0;
            }
        }
        return id;
    }


    public static int erroLista(ListaDeDesejosDAO listaDao, int id, int id_idoso, UsuarioDAO usuarioDao, itemDAO itemDao) throws SQLException {
        listaDeDesejos lista = listaDao.getById(id);

        while (lista == null) {
            exibirCabecalhoLista(id_idoso, usuarioDao, itemDao, listaDao);
            System.out.printf("Digite um id válido: ");
            id = scn.nextInt();
            scn.nextLine();
            lista = listaDao.getById(id);

            if(id == 0){
                return 0;
            }
        }
        return id;
    }

    /*
    Cria uma nova lista de desejos para o idoso logado.
     */
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

    /*
    Edita nome ou descrição de uma lista existente.
     */
    public static void editarLista(int id_idoso, UsuarioDAO usuarioDao, itemDAO itemDao, Scanner scn, ListaDeDesejosDAO listaDao) throws SQLException {

        while (true) {
            try {
                exibirCabecalhoLista(id_idoso, usuarioDao, itemDao, listaDao);

                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite o id da lista que deseja editar: ");
                int id = scn.nextInt();
                scn.nextLine();

                id = erroLista(listaDao, id, id_idoso, usuarioDao, itemDao);

                listaDao.update(id);

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    /*
    Permite ao idoso acessar uma lista específica por ID e gerenciar seus itens.
     */
    public static void acessarLista(Connection conn, Scanner scn, ListaDeDesejosDAO listaDao, itemDAO itemDao) throws SQLException {
        while(true) {
            try {
                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite o id da lista para acessar: ");
                int id = scn.nextInt();
                scn.nextLine();

                id = erroLista(listaDao, id);

                if (id == 0) {
                    System.out.println("Voltando...");
                    return;
                }
                    exibirListaDeDesejosById(id, listaDao, itemDao); //Acessa a lista de desejos e exibe dados da lista e seus itens (metodo da view -> Listas)
                    telaItens(id, conn);//Abre tela de gerenciamento de itens (metodo da view -> Listas)

            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }

    /*
    Exibe e permite gerenciar listas do idoso logado.
     */
    public static void telaListaDeDesejos(usuario u, Connection conn) throws SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        ListaDeDesejosDAO listaDao = new ListaDeDesejosDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        listaDeDesejos lista = null;

        while (true) {
            try {
                int id_idoso = u.getId(); //pega o id do usuáriio idoso
                exibirCabecalhoLista(id_idoso, usuarioDao, itemDao, listaDao); //exibe informação de cabeçalho das listas de desejos (metodo da view -> Listas)

                menuOpcaoLista();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> criarLista(id_idoso, scn, usuarioDao, lista, listaDao); //Cria uma nova lista e insere atributos (metodo da ListaDeDesejosController)
                    case 2 -> acessarLista(conn, scn, listaDao, itemDao); //Acessa lista específica e gerencia itens (metodo da ListaDeDesejosController)
                    case 3 -> editarLista(id_idoso, usuarioDao, itemDao,scn, listaDao); //Edita atributos da lista (metodo da ListaDeDesejosController)
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

    /*
    Permite ao familiar visualizar listas de todos os idosos que monitora
    e atualizar o status dos itens (PENDENTE → CONCLUÍDO).
     */
    public static void verListasDeIdosos(usuario u, Connection conn) throws SQLException {
        //DAOs para busca e atualização de historico
        MonitoraDAO monitoraDAO = new MonitoraDAO(conn);
        ListaDeDesejosDAO listaDAO = new ListaDeDesejosDAO(conn);
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        itemDAO itemDao = new itemDAO(conn);
        HistoricoDAO historicoDao = new HistoricoDAO(conn);

        do {
            try {
                int idFamiliar = usuarioDao.getIdFmById(u.getId()); //Busca ID específico do familiar na tabela filha
                List<idoso> idososIds = monitoraDAO.getIdososByFamiliar(idFamiliar); //Busca todos os idosos associados via tabela monitora

                if (idososIds.isEmpty()) {
                    System.out.println("Você ainda não monitora nenhum idoso");
                    return;
                }

                //Para cada idoso associado, busca e exibe suas listas
                for (idoso idosoId : idososIds) {
                    usuario idoso = usuarioDao.getUsByIds(idosoId.getId_idoso());
                    List<listaDeDesejos> listas = listaDAO.getByIdIdoso(idosoId.getId_idoso());
                    telaListaDosIsosos(listas, idoso); //exibe as listas dos idosos associados (metodo da view -> Listas)
                }

                System.out.println("Digite [0] para voltar");
                System.out.printf("Digite um ID para acessar a lista de desejos: ");
                opcao = scn.nextInt();
                scn.nextLine();

                erroLista(listaDao, opcao);

                while (true) {
                    exibirListaDeDesejosById(opcao, listaDAO, itemDao); //exibe lista escolhida por id e seus itens

                    //opcao para usuario logado escolher editar os historicos dos itens da lista
                    System.out.println("Digite [0] para voltar");
                    System.out.printf("Digite um ID para atualizar o status de um item: ");
                    opcao = scn.nextInt();
                    scn.nextLine();

                    if (opcao == 0) {
                        System.out.println("Voltando...");
                        return;
                    }

                    historicoDao.update(opcao); //metodo para atualizar o status no histórico
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }

        } while (opcao != 0); //fecha o loop quando escolhe opcao 0
    }
}
