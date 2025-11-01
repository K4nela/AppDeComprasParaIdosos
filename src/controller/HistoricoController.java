package controller;

import dao.HistoricoDAO;
import model.historico;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HistoricoController {
    private HistoricoDAO historicoDao;

    public HistoricoController(Connection conn) {
        this.historicoDao = new HistoricoDAO(conn);
    }

    public void registrarHistorico(historico registro) throws SQLException {
        historicoDao.insert(registro);
    }

    public List<historico> listarHistorico() throws SQLException {
        return historicoDao.getAll();
    }

    public historico buscarPorId(int id) throws SQLException {
        return historicoDao.getById(id);
    }

    public void deletarRegistro(int id) throws SQLException {
        historicoDao.delete(id);
    }
}

