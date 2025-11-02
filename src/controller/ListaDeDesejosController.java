package controller;

import dao.ListaDeDesejosDAO;
import dao.UsuarioDAO;
import model.idoso;
import model.listaDeDesejos;
import model.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        listaDeDesejos lista = null;

        int opcao = -1;

        while (true) {
            int id_idoso = u.getId();
            listaDao.getByIdIdoso(usuarioDao.getIdIdosoByUsuario(id_idoso));

            System.out.println("Digite [0] para voltar");
            System.out.println("1 - Opções da lista");
            System.out.printf("Digite a opcao q deseja: ");
            opcao = scn.nextInt();

            if (opcao == 0) {
                System.out.println("Voltando...");
                return;
            }

            if (opcao == 1) {
                menuOpcaoLista();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.println("Digite [0] para voltar");
                        System.out.println("---------CriandoLista---------");

                        System.out.printf("Nome da lista: ");
                        String nomeLista = scn.nextLine();

                        if(nomeLista.equals("0")){
                            System.out.println("Voltando...");
                            return;
                        }

                        System.out.printf("Descrição da lista: ");
                        String descricao = scn.nextLine();

                        if(descricao.equals("0")){
                            System.out.println("Voltando...");
                            return;
                        }
                        int idIdoso = usuarioDao.getIdIdosoByUsuario(id_idoso);
                        lista = new listaDeDesejos(idIdoso, nomeLista, descricao);
                        listaDao.save(lista);
                    }
                    case 2 -> listaDao.getByIdIdoso(usuarioDao.getIdIdosoByUsuario(id_idoso));

                    case 3 -> listaDao.update(lista.getId_lista());

                    case 4 -> System.out.println("Desenvolvendo...");

                    case 0 -> {
                        return;
                    }
                }
            }
        }
    }
}
