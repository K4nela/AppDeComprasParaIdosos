package dao;

import model.usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static view.Menus.menuUpdate;

public class UsuarioDAO extends BaseDAO<usuario> implements Base<usuario> {


    public UsuarioDAO(Connection conn){
        super(conn);
    }

    public static usuario criarUsuario(){
        //Cria o usuario
        usuario Usuario = new usuario("",
                LocalDate.of(1111,11,11),
                "",
                "",
                "",
                "");

    return Usuario;
    }

    /*
    Desenvolvendo o metodo salvar (usuario)
     */
    public void save(usuario u){
        //inserindo dados utilizando o metodo base da classe abstrata e interface
        String sql = "INSERT INTO usuario(nome, data_nasc, email, senha, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?);";

        try{
            super.conn.prepareStatement(sql);
            super.save(sql, u.getNome(), java.sql.Date.valueOf(u.getDataNasc()), u.getE_mail(), u.getSenha(), u.getEndereco(), u.getTelefone());

            System.out.println("Usuário salvo com sucesso!");
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível salvar o usuário!");
        }
    }

    /*
   Desenvolvendo o metodo update (usuario)
    */
    @Override
    public void update(int id){
        String sql;
        menuUpdate();
        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao){
            case 1://alterando nome de usuario
                sql = "UPDATE usuario SET nome = ? WHERE id = ?;";

                try {
                    System.out.println("Digite o novo nome: ");
                    String novoNome = scn.nextLine();

                    super.update(sql, novoNome, id);

                    System.out.println("Nome alterado com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar o nome!");
                }
                break;

            case 2://alterando data de nascimento
                sql = "UPDATE usuario SET data_nasc = ? WHERE id = ?;";

                try{
                    System.out.println("Digite sua data de nascimento: ");
                    String novaData = scn.nextLine();
                    super.update(sql, "data_nasc", novaData, id);

                    System.out.println("Data de nascimento atualizada com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar a data de nascimento!");
                }
                break;

            case 3://alterando email
                sql = "UPDATE usuario SET email = ? WHERE id = ?;";

                try{
                    System.out.println("Digite seu novo email: ");
                    String novoEmail = scn.nextLine();
                    super.update(sql, "email", novoEmail, id);

                    System.out.println("Email alterado com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar o email!");
                }
                break;

            case 4://alterando senha
                sql = "UPDATE usuario SET senha = ? WHERE id = ?;";

                try{
                    System.out.println("Digite sua nova senha: ");
                    String novaSenha = scn.nextLine();
                    super.update(sql, "senha", novaSenha, id);

                    System.out.println("Senha atualizada com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar a senha!");
                }
                break;

            case 5://alterando endereco
                sql = "UPDATE usuario SET endereco = ? WHERE id = ?;";

                try{
                    System.out.println("Digite seu novo endereço: ");
                    String novoEndereco = scn.nextLine();
                    super.update(sql, "endereco", novoEndereco, id);

                    System.out.println("Endereço alterado com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar o endereço!");
                }
                break;

            case 6://alterando telefone
                sql = "UPDATE usuario SET telefone = ? WHERE id = ?;";

                try{
                    System.out.println("Digite seu novo telefone: ");
                    String novoTelefone = scn.nextLine();
                    super.update(sql, "telefone", novoTelefone);

                    System.out.println("Telefone alterado com sucesso!");
                }catch (SQLException e){
                    System.out.println("ERRO! Não foi possível alterar o telefone!");
                }
                break;
        }
    }

    /*
    Desenvolvendo o metodo listar (usuario)
     */
    @Override
    public List<usuario> get(){
        List<usuario> uList = new ArrayList<>();
        String sql = "SELECT * FROM usuario ";

        try(PreparedStatement psmt = super.conn.prepareStatement(sql);
            ResultSet rlts = psmt.executeQuery()){

            while (rlts.next()){
                usuario u = new usuario(rlts.getInt("id"),
                                        rlts.getString( "nome"),
                                        rlts.getDate("data_nasc").toLocalDate(),
                                        rlts.getString("email"),
                                        rlts.getString("senha"),
                                        rlts.getString("endereco"),
                                        rlts.getString("telefone"));
                uList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar a tabela usuário!");
        }
        return uList;
    }


    /*
    Desenvolveno o metoddo para listar por id (usuario)
     */
    public usuario getById(int id) {
        usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nasc").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("endereco"),
                        rs.getString("telefone"));
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível buscar o usuário!");
            e.printStackTrace();
        }

        return usuario;
    }

    /*
    Desenvolvendo o metodo deletar (usuario)
    */
    @Override
    public void delete(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";

        try{
            System.out.println("Tem certeza que deseja excluir este usuário?");
            System.out.println("[Y/N]");
            String opcao = scn.nextLine();

            if(Objects.equals(opcao, "Y")){
                super.delete(sql, id);
            }

            System.out.println("Usuário excluído com sucesso!");
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível deletar o usuário!");
        }
    }
}//