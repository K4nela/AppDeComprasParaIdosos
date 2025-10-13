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
    @Override
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
    public void update(usuario u){
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

                    super.update(sql, novoNome, u.getId());

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
                    super.update(sql, "data_nasc", novaData, u.getId());

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
                    super.update(sql, "email", novoEmail, u.getId());

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
                    super.update(sql, "senha", novaSenha, u.getId());

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
                    super.update(sql, "endereco", novoEndereco, u.getId());

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
                    super.update(sql, "telefone", novoTelefone, u.getId());

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
        String sql = "SELECT * FROM usuario";

        try(PreparedStatement psmt = super.conn.prepareStatement(sql);ResultSet rlts = psmt.executeQuery()){

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
    Desenvolvendo o metodo deletar (usuario)
    */
    @Override
    public void delete(usuario u){
        String sql = "DELETE FROM usuario WHERE id = ?";

        try{
            System.out.println("Tem certeza que deseja excluir este usuário?");
            String opcao = scn.nextLine();

            if(Objects.equals(opcao, "sim")){
                super.delete(sql, u.getId());
            }

            System.out.println("Usuário excluído com sucesso!");
        }catch (SQLException e){
            System.out.println("ERRO! Não foi possível deletar o usuário!");
        }
    }
}