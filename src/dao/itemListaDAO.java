package dao;

import model.historico;
import model.itens;
import model.listaDeDesejos;
import model.status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class itemListaDAO extends CrudDAO<itens> {

    private static Connection conn;

    public itemListaDAO(Connection conn) {
        super(conn);
        this.conn = conn;
    }

    @Override
    public void save(itens item) throws SQLException {
        // insere o item no banco
        String sql = "INSERT INTO itens (nome_iten, descricao, quantidade, nome_loja, link) VALUES (?, ?, ?, ?, ?)";

        int id = super.save(sql,
                item.getNome_iten(),
                item.getDescricao(),
                item.getQuantidade(),
                item.getNome_loja(),
                item.getLink()
        );

        item.setId_iten(id);

        historico h = new historico(id, LocalDate.now(), status.PENDENTE);

        item.getHistoricos().add(h);

        HistoricoDAO historicoDao = new HistoricoDAO(conn);
        historicoDao.save(h);
    }


    @Override
    public void update(int id) throws SQLException {
        String sql = null;



    }

    @Override
    public List<itens> get() throws SQLException {
        return List.of();
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}

