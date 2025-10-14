package dao;

import model.idoso;
import model.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class idosoDAO extends BaseDAO<usuario> implements Base<usuario> {
    protected Connection conn;

    public idosoDAO(Connection conn) {
        super(conn);
    }


    @Override
    public void save(usuario u) throws SQLException {

    }

    @Override
    public void update(int id) throws SQLException {

    }

    @Override
    public List<usuario> get() throws SQLException {
        return List.of();
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
