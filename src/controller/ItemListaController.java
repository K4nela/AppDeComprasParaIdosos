//package controller;
//
//import dao.ItemListaDAO;
//import model.itens;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//public class ItemListaController {
//
//    private ItemListaDAO itemListaDao;
//
//    public ItemListaController(Connection conn) {this.itemListaDao = new ItemListaDAO(conn);}
//
//    public void criarItem(itens item) throws SQLException {ItemListaDAO.insert(item);} //desenvolvendo
//
//    public List<itens> listarTodos() throws SQLException {return ItemListaDAO.getAll();} //desenvolvendo
//
//    public itens buscarPorId(int id) throws SQLException {return ItemListaDAO.getById(id);} //desenvolvendo
//
//    public void atualizarItem(itens item) throws SQLException {ItemListaDAO.update(item);} //desenvolvendo
//
//    public void deletarItem(int id) throws SQLException {ItemListaDAO.delete(id);} //desenvolvendo
//}
