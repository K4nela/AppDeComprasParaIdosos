package view;

import dao.ListaDeDesejosDAO;
import dao.UsuarioDAO;
import dao.itemDAO;
import model.*;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Listas {

    public static void telaListaDosIsosos(List<listaDeDesejos> listas, usuario i) {
        for (listaDeDesejos lista : listas) {
            System.out.println("--------------------------------");
            System.out.println("lista de: " + i.getNome());
            System.out.println("================================");
            System.out.println("                            ID:" + lista.getId_lista() );
            System.out.println("        " + lista.getNomeLista());
            System.out.println("            " + lista.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("================================");
        }
    }

    public static void telaLista(listaDeDesejos lista){
        System.out.println("================================");
        System.out.println("                            ID:" + lista.getId_lista() );
        System.out.println("        " + lista.getNomeLista());
        System.out.println("            " + lista.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("================================");
    }

    public static void telaUsuarios(List<usuario> usuarios){
        System.out.println(" ");
        for (usuario u : usuarios) {
            System.out.println("|ID: " +  u.getId() + " | " + "Tipo: " +  u.getTipo() + " | " + "Nome: " + u.getNome() + " | " + "Data de nascimento: " + u.getDataNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + "Email: " + u.getE_mail() + " | " + "Telefone: " + u.getTelefone() + " | " + "Telefone: " + u.getTelefone() + " | " + "Endereço: " + u.getEndereco()+ " | ");
        }
        System.out.println(" ");
        System.out.println("Digite [0] para voltar");
    }

    public static void telaUsuarios(usuario u){
        System.out.println("====================================");
            System.out.println("                               ID:" + u.getId());
            System.out.println("            " + u.getNome());
            System.out.println("Tipo: " + u.getTipo());
            System.out.println("Data de nascimento: " + u.getDataNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Email: " + u.getE_mail());
            System.out.println("Telefone: " + u.getTelefone());
            System.out.println("Endereço: " + u.getEndereco());
            System.out.println("====================================");
    }

    public static void telaItem(item i){
        System.out.println("                            ID: " + i.getId_item());
        System.out.println("            " + i.getNome_item());
        System.out.println("Descrição: " + i.getDescricao());
        System.out.println("Quantidade: " + i.getQuantidade());
        System.out.println("Loja: " + i.getNome_loja());
        System.out.println("LINK: " + i.getLink());

        if (!i.getHistoricos().isEmpty()) {
            for (historico h : i.getHistoricos()) {
                System.out.printf("Data: %s |Status: %s\n", h.getData_historico().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), h.getStatus());
            }
        }
        System.out.println("--------------------------------");
    }

    public static void exibirCabecalhoLista(int id_idoso, UsuarioDAO usuarioDao, itemDAO itemDao, ListaDeDesejosDAO listaDao) throws SQLException {
        List<listaDeDesejos> listas = listaDao.getByIdIdoso(usuarioDao.getIdIdosoByUsuario(id_idoso));


        if (listas.isEmpty()) {
            System.out.println("ERRO! Não foi possível encontrar uma lista");
            return;
        }

        for (listaDeDesejos lista : listas) {

            List<item> itens = itemDao.getByLista(lista.getId_lista());
            lista.setItens(itens);

            telaLista(lista);
        }
    }

    public static void exibirListaDeDesejosById(int idLista, ListaDeDesejosDAO listaDao, itemDAO itemDao) throws SQLException {
        // pega a lista pelo id
        listaDeDesejos lista = listaDao.getById(idLista);

        if (lista == null) {
            System.out.println("Lista de desejos não encontrada!");
            return;
        }

        telaLista(lista);

        // busca os itens da lista pelo id_lista
        List<item> itens = itemDao.getByLista(lista.getId_lista());
        lista.setItens(itens); // popula os itens da lista

        if (itens.isEmpty()) {
            System.out.println("Nenhum item adicionado ainda!");
        } else {
            for (item i : itens) {

                telaItem(i);
            }
        }
        System.out.println("================================");;
    }

    public static void exibirListasDeDesejos(int id_idoso, UsuarioDAO usuarioDao, itemDAO itemDao, ListaDeDesejosDAO listaDao) throws SQLException {
        List<listaDeDesejos> listas = listaDao.getByIdIdoso(usuarioDao.getIdIdosoByUsuario(id_idoso));


        if (listas.isEmpty()) {
            System.out.println("ERRO! Não foi possível encontrar uma lista");
            return;
        }

        for (listaDeDesejos lista : listas) {

            List<item> itens = itemDao.getByLista(lista.getId_lista());
            lista.setItens(itens);

            telaLista(lista);

            if (itens.isEmpty()) {
                System.out.println("Nenhum item adicionado ainda!");
            } else {
                for (item i : itens) {

                    telaItem(i);
                }
            }
            System.out.println("================================");
        }
    }

    public static void exibirListaUsuarios(UsuarioDAO usuarioDao) {
        List<usuario> usuarios = usuarioDao.get();

        if (usuarios.isEmpty()) {
            System.out.println("ERRO! Não foi possível encontrar o usuário");
            return;
        }
        telaUsuarios(usuarios);
    }

    public static void exibirUsuarioPorId(UsuarioDAO usuarioDao, int id) {
        usuario u = usuarioDao.getById(id);

        if (u == null) {
            System.out.println("ERRO! Não foi possível encontrar o usuário");
            return;
        }

        telaUsuarios(u);
    }
}


