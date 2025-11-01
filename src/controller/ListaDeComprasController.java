package controller;

import dao.ListaDeComprasDAO;
import model.listaDeCompras;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListaDeComprasController {
    private ListaDeComprasDAO listaDao;

    public ListaDeComprasController(Connection conn) {this.listaDao = new ListaDeComprasDAO(conn);}

    public void criarLista(listaDeCompras lista) throws SQLException {listaDao.insert(lista);}

    public List<listaDeCompras> listarTodas() throws SQLException {return listaDao.getAll();}

    public listaDeCompras buscarPorId(int id) throws SQLException {return listaDao.getById(id);}

    public void atualizarLista(listaDeCompras lista) throws SQLException {listaDao.update(lista);}

    public void deletarLista(int id) throws SQLException {listaDao.delete(id);}

}
