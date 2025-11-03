package view;

import dao.ListaDeDesejosDAO;
import dao.UsuarioDAO;
import dao.itemDAO;
import model.historico;
import model.item;
import model.listaDeDesejos;
import model.usuario;

import java.sql.SQLException;
import java.util.List;

public abstract class ToStrings {

    public static void exibirListaDeDesejosById(int idLista, ListaDeDesejosDAO listaDao, itemDAO itemDao) throws SQLException {
        // pega a lista pelo id
        listaDeDesejos lista = listaDao.getById(idLista);

        if (lista == null) {
            System.out.println("Lista de desejos não encontrada!");
            return;
        }

        System.out.println("------- Lista de Desejos -------");
        System.out.println("ID: " + lista.getId_lista());
        System.out.println("Nome da lista: " + lista.getNomeLista());
        System.out.println("Data de criação: " + lista.getDataCriacao());
        System.out.println("Observações: " + lista.getDescricao());
        System.out.println("-------------------------------");

        // busca os itens da lista pelo id_lista
        List<item> itens = itemDao.getByLista(lista.getId_lista());
        lista.setItens(itens); // popula os itens da lista

        if (itens.isEmpty()) {
            System.out.println("Nenhum item adicionado ainda!");
        } else {
            for (item i : itens) {
                System.out.println("========================================");
                System.out.println("ID: " + i.getId_item());
                System.out.println("Nome: " + i.getNome_item());
                System.out.println("Descrição: " + i.getDescricao());
                System.out.println("Quantidade: " + i.getQuantidade());
                System.out.println("Loja: " + i.getNome_loja());
                System.out.println("LINK: " + i.getLink());

                // exibe históricos
                if (!i.getHistoricos().isEmpty()) {
                    System.out.println("Histórico:");
                    for (historico h : i.getHistoricos()) {
                        System.out.printf("Data: %s | Status: %s\n", h.getData_historico(), h.getStatus());
                    }
                }
                System.out.println("========================================");
            }
        }
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

            System.out.println("------- Lista de Desejos -------");
            System.out.println("id: " + lista.getId_lista());
            System.out.println("Nome da lista: " + lista.getNomeLista());
            System.out.println("Data de criação: " + lista.getDataCriacao());
            System.out.println("Observações: " + lista.getDescricao());
            System.out.println("-------------------------------");

            if (itens.isEmpty()) {
                System.out.println("Nenhum item adicionado ainda!");
            } else {
                for (item i : itens) {
                    System.out.println("========================================");
                    System.out.println("ID: " + i.getId_item());
                    System.out.println("Nome: " + i.getNome_item());
                    System.out.println("Descrição: " + i.getDescricao());
                    System.out.println("Quantidade: " + i.getQuantidade());
                    System.out.println("Loja: " + i.getNome_loja());
                    System.out.println("LINK: " + i.getLink());

                    // exibe históricos
                    if (!i.getHistoricos().isEmpty()) {
                        System.out.println("Histórico:");
                        for (historico h : i.getHistoricos()) {
                            System.out.printf("Data: %s | Status: %s\n", h.getData_historico(), h.getStatus());
                        }
                    }
                    System.out.println("========================================");
                }
            }
        }
    }

    public static void exibirListaUsuarios(UsuarioDAO usuarioDao) {
        List<usuario> usuarios = usuarioDao.get();

        if (usuarios.isEmpty()) {
            System.out.println("ERRO! Não foi possível encontrar o usuário");
            return;
        }

        System.out.println("---------UsuáriosCadastrados--------");
        for (usuario u : usuarios) {
            System.out.println("====================================");
            System.out.println("ID: " + u.getId());
            System.out.println("Tipo: " + u.getTipo());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Data de nascimento" + u.getDataNasc());
            System.out.println("Email: " + u.getE_mail());
            System.out.println("Telefone: " + u.getTelefone());
            System.out.println("Endereço: " + u.getEndereco());
            System.out.println("====================================");
        }
    }

    public static void exibirUsuarioPorId(UsuarioDAO usuarioDao, int id) {
        usuario u = usuarioDao.getById(id);

        if (u == null) {
            System.out.println("ERRO! Não foi possível encontrar o usuário");
            return;
        }

        System.out.println("--------PerfilDoUsuário--------");
        System.out.println("ID: " + u.getId());
        System.out.println("Nome: " + u.getNome());
        System.out.println("Tipo: " + u.getTipo());
        System.out.println("Data de Nascimento: " + u.getDataNasc());
        System.out.println("Email: " + u.getE_mail());
        System.out.println("Endereço: " + u.getEndereco());
        System.out.println("Telefone: " + u.getTelefone());
        System.out.println("-------------------------------");
    }
}


