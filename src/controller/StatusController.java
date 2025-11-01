package controller;

import dao.StatusDAO;
import model.status;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StatusController {
    private StatusDAO statusDao;

    public StatusController(Connection conn) { this.statusDao = new StatusDAO(conn);}

    public void criarStatus(status s) throws SQLException { statusDao.insert(s);}

    public List<status> listarTodos() throws SQLException { return statusDao.getAll();}

    public status buscarPorId(int id) throws SQLException { return statusDao.getById(id);}

    public void atualizarStatus(status s) throws SQLException { statusDao.update(s);}

    public void deletarStatus(int id) throws SQLException { statusDao.delete(id);}
}
