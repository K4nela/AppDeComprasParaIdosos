package dao;

import model.historico;
import model.item;
import model.status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static view.Menus.menuUpdateItem;

public class itemDAO extends CrudDAO<item> {

    private static Connection conn;

    public itemDAO(Connection conn) {
        super(conn);
        this.conn = conn;
    }

    @Override
    public void save(item item) throws SQLException {
        // insere o item no banco
        String sql = "INSERT INTO item (id_lista, nome_item, descricao, quantidade, nome_loja, link) VALUES (?, ?, ?, ?, ?, ?)";

        int id = super.save(sql,
                item.getId_lista(),
                item.getNome_item(),
                item.getDescricao(),
                item.getQuantidade(),
                item.getNome_loja(),
                item.getLink()
        );
        item.setId_item(id);

        historico h = new historico();
        h.setId_item(item.getId_item());
        h.setData_historico(LocalDate.now());
        h.setStatus(status.PENDENTE);

        item.getHistoricos().add(h);

        HistoricoDAO historicoDao = new HistoricoDAO(conn);
        historicoDao.save(h);
    }

    @Override
    public void update(int id) throws SQLException {
        String sql;

        while(true){
            menuUpdateItem();
            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1 -> {
                    try {
                        sql = "UPDATE item SET nome_item = ? WHERE id_item = ?;";
                        System.out.println("Digite [0] para voltar");
                        System.out.printf("Digite o novo nome do item: ");
                        String novoNome = scn.nextLine();

                        if (novoNome.equals("0")) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            super.update(sql, novoNome, id);
                            System.out.println("Nome alterado com sucésso!");
                            return;
                        }
                    } catch (SQLException e) {
                        System.out.println("ERRO! Não foi possível alterar o nome do item");
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    try {
                        sql = "UPDATE item SET descricao = ? WHERE id_item = ?;";
                        System.out.println("Digite [0] para voltar");
                        System.out.printf("Digite o novo descrição do item: ");
                        String novaDesc = scn.nextLine();

                        if (novaDesc.equals("0")) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            System.out.println("Descrição alterada com sucésso!");
                            super.update(sql, novaDesc, id);
                            return;
                        }
                    } catch (SQLException e) {
                        System.out.println("ERRO! Não foi possível alterar a descrição do item");
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    try {
                        sql = "UPDATE item SET quantidade = ? WHERE id_item = ?;";
                        System.out.println("Digite [0] para voltar");
                        System.out.printf("Digite o nova quantidade do item: ");
                        int novaQuant = scn.nextInt();
                        scn.nextLine();

                        if (novaQuant == 0) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            super.update(sql, novaQuant, id);
                            System.out.println("Quantidade alterado com sucésso!");
                            return;
                        }
                    } catch (SQLException e) {
                        System.out.println("ERRO! Não foi possível alterar a quantidade do item");
                        e.printStackTrace();
                    }
                }

                case 4 -> {
                    try {
                        sql = "UPDATE item SET nome_loja = ? WHERE id_item = ?;";
                        System.out.println("Digite [0] para voltar");
                        System.out.printf("Digite o novo nome da loja do item: ");
                        String novoNomeLoja = scn.nextLine();

                        if (novoNomeLoja.equals("0")) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            super.update(sql, novoNomeLoja, id);
                            System.out.println("Nome da loja alterado com sucésso!");
                            return;
                        }
                    } catch (SQLException e) {
                        System.out.println("ERRO! Não foi possível alterar o nome da loja do item");
                        e.printStackTrace();
                    }
                }

                case 5 -> {
                    try {
                        sql = "UPDATE item SET link = ? WHERE id_item = ?;";
                        System.out.println("Digite [0] para voltar");
                        System.out.printf("Digite o novo link do item: ");
                        String novoLink = scn.nextLine();

                        super.update(sql, novoLink, id);

                        if (novoLink.equals("0")) {
                            System.out.println("Voltando...");
                            return;
                        } else {
                            System.out.println("link alterado com sucésso!");
                            return;
                        }
                    } catch (SQLException e) {
                        System.out.println("ERRO! Não foi possível alterar o link do item");
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

    @Override
    public List<item> get() throws SQLException {
        List<item> item = new ArrayList<>();
        String sql = "SELECT * FROM itens;";

        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                item i = new item(
                rs.getInt("id_item"),
                rs.getInt("id_lista"),
                rs.getString("nome_item"),
                rs.getString("descricao"),
                rs.getInt("quantidade"),
                rs.getString("nome_loja"),
                rs.getString("link")
                );

                List<historico> historicosItem = getByItemId(i.getId_item());
                i.setHistoricos(historicosItem);

                if(!historicosItem.isEmpty()){
                    i.setStatus(historicosItem.get(historicosItem.size() - 1).getStatus());
                }
                item.add(i);
            }
        }catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar os item.");
            e.printStackTrace();
        }
        return item;
    }

    public item getById(int idItem) throws SQLException {
        String sql = "SELECT * FROM item WHERE id_item = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item i = new item(
                            rs.getInt("id_item"),
                            rs.getInt("id_lista"),
                            rs.getString("nome_item"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getString("nome_loja"),
                            rs.getString("link")
                    );

                    // Pega o histórico do item
                    List<historico> historicosItem = getByItemId(i.getId_item());
                    i.setHistoricos(historicosItem);

                    if (!historicosItem.isEmpty()) {
                        i.setStatus(historicosItem.get(historicosItem.size() - 1).getStatus());
                    }

                    return i;
                }
            }
        }
        return null; // se não encontrar
    }

    public List<historico> getByItemId(int idItem) throws SQLException {
        List<historico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historico WHERE id_item = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historico h = new historico();
                    h.setId_historico(rs.getInt("id_historico"));
                    h.setData_historico(rs.getDate("data_historico").toLocalDate());
                    h.setStatus(status.valueOf(rs.getString("status")));
                    lista.add(h);
                }
            }
        }
        return lista;
    }

    public List<item> getByLista(int idLista) throws SQLException {
        List<item> item = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE id_lista = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLista);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    item i = new item(
                            rs.getInt("id_item"),
                            rs.getInt("id_lista"),
                            rs.getString("nome_item"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getString("nome_loja"),
                            rs.getString("link")
                    );

                    // associa históricos
                    i.setHistoricos(getByItemId(i.getId_item()));

                    if (!i.getHistoricos().isEmpty()) {
                        i.setStatus(i.getHistoricos().get(i.getHistoricos().size() - 1).getStatus());
                    }

                    item.add(i);
                }
            }
        }
        return item;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM item WHERE id_item = ?;";
        try{
            System.out.println("Tem certeza que deseja excluir este item?");
            System.out.println("[Y/N]");
            String opcao = scn.nextLine();

            if(Objects.equals(opcao, "Y") || Objects.equals(opcao, "y")){
                super.update(sql, id);
                System.out.println("Item excluído com sucesso!");
            }else{
                System.out.println("Voltando...");
            }
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível deletar o item!");
            e.printStackTrace();
        }
    }
}

