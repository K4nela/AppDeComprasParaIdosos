package dao;

import model.idoso;
import model.monitora;
import model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO para a tabela "monitora" que associa familiares e idososSegue o padrão de uso de conexão do projeto e reutiliza UsuarioDAO para mapear usuários.
public class MonitoraDAO extends CrudDAO<monitora> {

    public MonitoraDAO(Connection conn) {
        super(conn);
    }
    
    //Vincula um familiar a um idoso (insere registro na tabela monitora).
    public void vincular(int idFamiliar, int idIdoso) {
        // A tabela 'monitora' no schema possui somente as colunas (id_familiar, id_idoso)
        String sql = "INSERT INTO monitora (id_familiar, id_idoso) VALUES (?, ?)";

        try {
            super.save(sql, idFamiliar, idIdoso);
            System.out.println("Associação entre familiar e idoso realizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível associar familiar e idoso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Desvincula por par familiar/idoso.
    public void delete(int idFamiliar, int idIdoso) {
        String sql = "DELETE FROM monitora WHERE id_familiar = ? AND id_idoso = ?";
        try {
            super.delete(sql, idFamiliar, idIdoso);
            System.out.println("Associação (familiar, idoso) removida com sucesso.");
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível remover a associação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Lista os familiares associados a um determinado idoso.
    public List<usuario> getByFm(int idIdoso) {
        List<usuario> familiares = new ArrayList<>();
        // O parâmetro recebido normalmente é o id do usuário (usuario.id) do idoso.
        // Fazer JOIN para localizar o id_usuario dos familiares associados.
        String sql = "SELECT f.id_usuario AS id_usuario FROM monitora m JOIN idoso i ON m.id_idoso = i.id_idoso JOIN familiar f ON m.id_familiar = f.id_familiar WHERE i.id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idIdoso); // idIdoso aqui é o id do usuário do idoso
            try (ResultSet rs = ps.executeQuery()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
                while (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    usuario u = usuarioDAO.getById(idUsuario);
                    if (u != null) familiares.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar familiares do idoso: " + e.getMessage());
            e.printStackTrace();
        }

        return familiares;
    }
    
    //Lista os idosos associados a um determinado familiar.
    public List<usuario> getById(int idFamiliar) {
        List<usuario> idosos = new ArrayList<>();
        String sql = "SELECT i.id_usuario AS id_usuario FROM monitora m  JOIN familiar f ON m.id_familiar = f.id_familiar JOIN idoso i ON m.id_idoso = i.id_idoso WHERE f.id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFamiliar);

            try (ResultSet rs = ps.executeQuery()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

                while (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    usuario u = usuarioDAO.getById(idUsuario);
                    if (u != null) idosos.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar idosos do familiar: " + e.getMessage());
            e.printStackTrace();
        }

        return idosos;
    }

    public List<idoso> getIdososByFamiliar(int idFamiliar) throws SQLException {
        List<idoso> idosos = new ArrayList<>();
        String sql = "SELECT i.id_idoso FROM idoso i JOIN monitora m ON i.id_idoso = m.id_idoso WHERE m.id_familiar = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFamiliar);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    idoso id = new idoso(rs.getInt("id_idoso"));
                    idosos.add(id);
                }
            }
        }
        return idosos;
    }

    //Salva o vinculo entre usuarios
    @Override
    public void save(monitora u) throws SQLException {
        int idFamiliar = u.getId_familiar();
        int idIdoso = u.getId_idoso();

        // A tabela 'monitora' no schema possui somente as colunas (id_familiar, id_idoso)
        String sql = "INSERT INTO monitora (id_familiar, id_idoso) VALUES (?, ?)";

        try {
            super.save(sql, idFamiliar, idIdoso);
            System.out.println("Associação entre familiar e idoso realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível associar familiar e idoso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Lista todas os vinculos entre usuarios
    @Override
    public List<monitora> get() throws SQLException {
        List<monitora> lista = new ArrayList<>();
        String sql = "SELECT * FROM monitora";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                monitora m = new monitora();
                m.setId_familiar(rs.getInt("id_familiar"));
                m.setId_idoso(rs.getInt("id_idoso"));
                lista.add(m);
            }
        }
        return lista;
    }

    //Desvincula usuarios associados
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM monitora WHERE id = ?";
        try {
            super.delete(sql, id);
            System.out.println("Associação removida com sucesso.");

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível remover a associação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id) throws SQLException {
    }
}

