package dao;

import model.listaDeDesejos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static view.Menus.menuUpdateLista;

public class ListaDeDesejosDAO extends CrudDAO<listaDeDesejos> {

    public ListaDeDesejosDAO(Connection conn) {
        super(conn);
        this.conn = conn;
    }

    @Override
    public void save(listaDeDesejos lista) throws SQLException {
        String sql = "INSERT INTO listadedesejos (id_idoso, nome, dataCriacao, descricao) VALUES (?, ?, ?, ?)";

        try{
          int idLista = super.save( sql,
                  lista.getId_idoso().getId(),
                  lista.getNomeLista(),
                  lista.getDataCriacao(),
                  lista.getDescricao()
          );
          lista.setId_lista(idLista);
          System.out.println("Lista criada com sucesso!");

        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível criar a lista");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id) throws SQLException {
        String sql ;

        while(true){
            try{
                menuUpdateLista();
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao){
                    case 1 -> {
                        try {
                            sql = "UPDATE listadedesejos SET nome = ? WHERE id_lista = ?";
                            System.out.println("Digite [0] para sair.");
                            System.out.printf("Digite o novo nome da lista: ");
                            String novoNome = scn.nextLine();

                            super.update(sql, novoNome, id);

                            if (novoNome.equals("0")) {
                                System.out.println("Voltando...");
                                return;
                            } else {
                                System.out.println("Nome alterado com sucesso!");
                            }
                        }catch (SQLException e ){
                            System.out.println("ERRO! Não foi possível alterar o nome!");
                            e.printStackTrace();
                        }
                    }

                    case 2 -> {
                        sql = "UPDATE listadedesejos SET descricao = ? WHERE id_lista = ?";
                        System.out.println("Digite [0] para sair.");
                        System.out.printf("Digite a nova descrição da lista: ");
                        String novaDescricao = scn.nextLine();

                        super.update(sql, novaDescricao, id);

                        if (novaDescricao.equals("0")) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            System.out.println("Descrição alterada com sucesso!");
                        }
                    }

                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                }
            }catch (SQLException e){
                System.out.println("ERRO! Não foi possível fazer o update!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<listaDeDesejos> get() throws SQLException {
        List<listaDeDesejos> listas = new ArrayList<>();
        String sql = "SELECT * FROM listadedesejos";

        try(PreparedStatement ps = super.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            if (!rs.isBeforeFirst()) { // checa se não tem nenhum registro
                System.out.println("Nenhuma lista de desejos encontrada!");
                return null;
            }

            while (rs.next()) {
                System.out.println(
                                "\n-----------Lista De Desejos-----------" +
                                "\nnome: " + rs.getString("nome") +
                                "\ndata de criação: " + rs.getDate("dataCriacao").toLocalDate() +
                                "\ndescrição: " + rs.getString("descricao") +
                                "\n----------------------------------------"
                );
            }
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível listar a lista de Desejos");
            e.printStackTrace();
        }
        return listas;
    }

    public listaDeDesejos getById(int id) throws SQLException {
        String sql = "SELECT * FROM listadedesejos WHERE id_lista = ?";
        listaDeDesejos lista = null;

        try(PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {

                if (!rs.isBeforeFirst()) { // checa se não tem nenhum registro
                    System.out.println("Nenhuma lista de desejos encontrada!");
                    return null;
                }

                if (rs.next()) {
                    lista = new listaDeDesejos();
                    lista.setNomeLista(rs.getString("nome"));
                    lista.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    lista.setDescricao(rs.getString("descricao"));
                }
            }
            return lista;

        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível listar a lista de Desejos atraves do id da lista");
            e.printStackTrace();
        }
        return null;
    }

    public List<listaDeDesejos> getByIdIdoso(int id) throws SQLException {
        List<listaDeDesejos> listas = new ArrayList<>();
        String sql = "SELECT * FROM listadedesejos WHERE id_idoso = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) { // checa se não tem nenhum registro
                System.out.println("Nenhuma lista de desejos encontrada!");
                return null;
            }

            while (rs.next()) {
                System.out.println(
                        "\n-----------Lista De Desejos-----------" +
                        "\nnome: " + rs.getString("nome") +
                        "\ndata de criação: " + rs.getDate("dataCriacao").toLocalDate() +
                        "\ndescrição: " + rs.getString("descricao") +
                        "\n----------------------------------------"
                );
            }
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível listar a lista de Desejos através de id");
            e.printStackTrace();
        }
        return listas;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM listadedesejos WHERE id_lista=?";
        try{
            System.out.println("Tem certeza que deseja excluir esta lista de desejos?");
            System.out.println("[Y/N]");
            String opcao = scn.nextLine();

            if(Objects.equals(opcao, "Y")){
                super.update(sql, id);
                System.out.println("Lista de desejos excluída com sucesso!");
            }else{
                System.out.println("Voltando...");
            }
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível deletar a lista de desejos!");
            e.printStackTrace();
        }
    }
}
