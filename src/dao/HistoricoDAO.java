package dao;

import model.historico;
import model.status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

import static view.Menus.menuTipoStatus;

//alterar a tabela historico no banco de dados
public class HistoricoDAO extends CrudDAO<historico> {
    private Connection conn;

    public HistoricoDAO(Connection conn) {
        super(conn);
        this.conn = conn;
    }

    @Override
    public void save(historico h) throws SQLException {
        String sql = "INSERT INTO historico (id_item, data_historico, status) VALUES (?,?,?)";

        try {
            int idHistorico = super.save(sql,
                    h.getId_item(),
                    h.getData_historico(),
                    h.getStatus().name()
            );
            h.setId_historico(idHistorico);
            System.out.println("Historico criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível criar o histórico");
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id) throws SQLException {
        String sql = "UPDATE historico SET status = ? WHERE id_item= ?;";
        status novoStatus = null;

        try{
            menuTipoStatus();
            int opcao = scn.nextInt();
            scn.nextLine();

            if (opcao == 0) {
                System.out.println("Voltando...");
                return;
            }

            switch (opcao) {
                case 1 -> novoStatus = status.PENDENTE;
                case 2 -> novoStatus = status.EM_ANDAMENTO;
                case 3 -> novoStatus = status.CONCLUIDO;
                case 4 -> novoStatus = status.CANCELADO;
                case 0 -> {
                    System.out.println("Voltando...");
                    return;
                }
                default -> {
                    System.out.println("ERRO! Opção inválida");
                    return;
                }
            }
            super.update(sql, novoStatus.name(), id);
        }catch (InputMismatchException e){
            System.out.println("ERRO! Digite apenas números");
            scn.nextLine();
        }
    }

    @Override
    public List<historico> get() throws SQLException {
        List<historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                historico h = new historico();
                h.setId_historico(rs.getInt("id_historico"));
                h.setId_item(rs.getInt("id_item"));
                h.setData_historico(rs.getDate("data_historico").toLocalDate());
                h.setStatus(status.valueOf(rs.getString("status")));
                historicos.add(h);
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar os históricos!");
            e.printStackTrace();
        }

        return historicos;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM historico WHERE id_historico=?";
        try {
            System.out.println("Tem certeza que deseja excluir o historico?");
            System.out.println("[Y/N]");
            String opcao = scn.nextLine();

            if (Objects.equals(opcao, "Y") || Objects.equals(opcao, "y")) {
                super.update(sql, id);
                System.out.println("Historico excluído com sucesso!");
            } else {
                System.out.println("Voltando...");
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível deletar o historico!");
            e.printStackTrace();
        }
    }

}
