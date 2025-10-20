package dao;

import model.administrador;
import model.familiar;
import model.idoso;
import model.usuario;

import java.sql.*;
import java.util.*;

import static view.Menus.menuUpdate;

public class UsuarioDAO extends CrudDAO<usuario> implements CrudInterface<usuario> {

    public UsuarioDAO(Connection conn) {
        super(conn);
    }

    /*
    Desenvolvendo o metodo salvar (usuario)
     */
    @Override
    public void save(usuario u) {
        // SQL para inserir no usuário
        String sqlUsuario = "INSERT INTO usuario(nome, data_nasc, email, senha, endereco, telefone, tipo) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            // chama o save da BaseDAO e pega o ID gerado
            int idUsuario = super.save(sqlUsuario,
                    u.getNome(),
                    java.sql.Date.valueOf(u.getDataNasc()),
                    u.getE_mail(),
                    u.getSenha(),
                    u.getEndereco(),
                    u.getTelefone(),
                    u.getTipo());

            // SQL para inserir na tabela específica
            String sqlTipoUsuario = switch (u.getTipo()) {
                case "idoso" -> "INSERT INTO idoso(id_usuario) VALUES (?);";
                case "familiar" -> "INSERT INTO familiar(id_usuario) VALUES (?);";
                case "administrador" -> "INSERT INTO administrador(id_usuario) VALUES (?);";
                default -> throw new IllegalArgumentException("Tipo de usuário inválido!");
            };

            try (PreparedStatement psTipo = conn.prepareStatement(sqlTipoUsuario)) {
                psTipo.setInt(1, idUsuario);
                psTipo.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível salvar o usuário!");
            e.printStackTrace();
        }
    }

    /*
    Desenvolvendo o metodo update (usuario)
    */
    @Override
    public void update(int id) {
        String sql;

        while (true) {
            try {
                menuUpdate();
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {

                    case 1://alterando nome de usuario
                        sql = "UPDATE usuario SET nome = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite [0] para sair");
                            System.out.println("Digite o novo nome: ");
                            String novoNome = scn.nextLine();

                            validarEntrada(novoNome);

                            super.update(sql, novoNome, id);

                            if (novoNome.equals("0")) {
                                System.out.println("Saindo...");
                                return;
                            } else {
                                System.out.println("Nome alterado com sucesso!");
                            }

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o nome!");
                            e.printStackTrace();
                        }
                        break;

                    case 2://alterando data de nascimento
                        sql = "UPDATE usuario SET data_nasc = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite sua data de nascimento: ");
                            String novaData = scn.nextLine();

                            validarEntrada(novaData);

                            super.update(sql, "data_nasc", novaData, id);
                            System.out.println("Data de nascimento atualizada com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar a data de nascimento!");
                            e.printStackTrace();
                        }
                        break;

                    case 3://alterando email
                        sql = "UPDATE usuario SET email = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite [0] para sair");
                            System.out.println("Digite seu novo email: ");
                            String novoEmail = scn.nextLine();

                            validarEntrada(novoEmail);

                            super.update(sql, "email", novoEmail, id);
                            System.out.println("Email alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o email!");
                            e.printStackTrace();
                        }
                        break;

                    case 4://alterando senha
                        sql = "UPDATE usuario SET senha = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite [0] para sair");
                            System.out.println("Digite sua nova senha: ");
                            String novaSenha = scn.nextLine();

                            validarEntrada(novaSenha);

                            super.update(sql, "senha", novaSenha, id);
                            System.out.println("Senha atualizada com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar a senha!");
                            e.printStackTrace();
                        }
                        break;

                    case 5://alterando endereco
                        sql = "UPDATE usuario SET endereco = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite seu novo endereço: ");
                            String novoEndereco = scn.nextLine();

                            validarEntrada(novoEndereco);

                            super.update(sql, "endereco", novoEndereco, id);
                            System.out.println("Endereço alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o endereço!");
                            e.printStackTrace();
                        }
                        break;

                    case 6://alterando telefone
                        sql = "UPDATE usuario SET telefone = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite seu novo telefone: ");
                            String novoTelefone = scn.nextLine();

                            validarEntrada(novoTelefone);

                            super.update(sql, "telefone", novoTelefone);
                            System.out.println("Telefone alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o telefone!");
                            e.printStackTrace();
                        }
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        return;
                }
            } catch (Exception e) {
                System.out.println("ERRO! Digite apenas números.");
                scn.nextLine();
            }
        }
    }

    /*
    Desenvolvendo metodo de validação de valores do update (usuario)
     */
    public static String validarEntrada(String entrada) {

        if (entrada == "0") {
            return null;
        }

        Scanner scn = new Scanner(System.in);
        while (entrada == null || entrada.trim().isEmpty()) {
            System.out.print("ERRO! O campo não pode ficar vazio! Digite novamente: ");
            entrada = scn.nextLine();
        }
        return entrada;

    }

    /*
    Desenvolvendo o metodo listar (usuario)
     */
    @Override
    public List<usuario> get() {
        List<usuario> uList = new ArrayList<>();
        String sql = "SELECT * FROM usuario ";

        try (PreparedStatement psmt = super.conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                        "\n-------Usuário-------\n" +
                                "id = " + rs.getInt("id") +
                                "\nnome = " + rs.getString("nome") +
                                "\ndata de nascimento = " + rs.getDate("data_nasc").toLocalDate() +
                                "\nemail = " + rs.getString("email") +
                                "\nsenha = " + rs.getString("senha") +
                                "\nendereço = " + rs.getString("endereco") +
                                "\ntelefone = " + rs.getString("telefone") +
                                "\ntipo = " + rs.getString("tipo") +
                                "\n--------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar a tabela usuário!");
            e.printStackTrace();
        }
        return uList;
    }

    /*
    Desenvolvendo o metodo deletar (usuario)
    */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            System.out.println("Tem certeza que deseja excluir este usuário?");
            System.out.println("[Y/N]");
            String opcao = scn.nextLine();

            if (Objects.equals(opcao, "Y")) {
                super.delete(sql, id);
                System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Voltando...");
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível deletar o usuário!");
            e.printStackTrace();
        }
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
                String tipo = rs.getString("tipo");

                switch (tipo) {

                    case "idoso" -> usuario = new idoso(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "familiar" -> usuario = new familiar(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "administrador" -> usuario = new administrador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível buscar o usuário!");
            e.printStackTrace();
        }
        return usuario;
    }

    /*
    Desenvolvendo o metodo para login (usuario)
     */
    public usuario login(String email, String senha) {
        usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");

                switch (tipo) {

                    case "idoso" -> usuario = new idoso(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "familiar" -> usuario = new familiar(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "administrador" -> usuario = new administrador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível realizar o login!");
            e.printStackTrace();
        }
        return usuario;
    }

    /*
    Desenvolvendo o metodo para validar liogin (senha)
     */
    public usuario getBySenha(String senha) {
        usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE senha = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");

                switch (tipo) {

                    case "idoso" -> usuario = new idoso(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "familiar" -> usuario = new familiar(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "administrador" -> usuario = new administrador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível realizar o login!");
            e.printStackTrace();
        }
        return usuario;
    }

    /*
    Desenvolvendo o metodo para validar login (email)
    */
    public usuario getByEmail(String email) {
        usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");

                switch (tipo) {

                    case "idoso" -> usuario = new idoso(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "familiar" -> usuario = new familiar(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );

                    case "administrador" -> usuario = new administrador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO! E-mail não cadastrado!");
            e.printStackTrace();
        }
        return usuario;
    }

}