package dao;

import model.administrador;
import model.familiar;
import model.idoso;
import model.usuario;

import java.sql.*;
import java.util.*;

import static view.Menus.menuUpdateUsuario;

public class UsuarioDAO extends CrudDAO<usuario> implements CrudInterface<usuario> {

    public UsuarioDAO(Connection conn) {
        super(conn);
    }

    //Desenvolvendo o metodo salvar (usuario)
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

    //Desenvolvendo o metodo update (usuario)
    @Override
    public void update(int id) {
        String sql;

        while (true) {
            try {
                menuUpdateUsuario();
                int opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao) {

                    case 1 -> {//alterando nome de usuario
                        sql = "UPDATE usuario SET nome = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite [0] para sair");
                            System.out.println("Digite o novo nome: ");
                            String novoNome = scn.nextLine();

                            novoNome = validarEntrada(novoNome);

                            super.update(sql, novoNome, id);

                            if (novoNome.equals("0")) {
                                System.out.println("Voltando...");
                                return;
                            } else {
                                System.out.println("Nome alterado com sucesso!");
                            }

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o nome!");
                            e.printStackTrace();
                        }
                    }

                    case 2 -> { //alterando data de nascimento
                        sql = "UPDATE usuario SET data_nasc = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite sua data de nascimento: ");
                            String novaData = scn.nextLine();

                            novaData = validarEntrada(novaData);

                            super.update(sql, "data_nasc", novaData, id);
                            System.out.println("Data de nascimento atualizada com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar a data de nascimento!");
                            e.printStackTrace();
                        }
                    }

                    case 3 -> { //alterando email
                        sql = "UPDATE usuario SET email = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite [0] para sair");
                            System.out.println("Digite seu novo email: ");
                            String novoEmail = scn.nextLine();

                            novoEmail = validarEntrada(novoEmail);

                            super.update(sql, "email", novoEmail, id);
                            System.out.println("Email alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o email!");
                            e.printStackTrace();
                        }
                    }

                    case 4 -> { //alterando senha
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
                    }

                    case 5 -> {//alterando endereco
                        sql = "UPDATE usuario SET endereco = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite seu novo endereço: ");
                            String novoEndereco = scn.nextLine();

                            novoEndereco = validarEntrada(novoEndereco);

                            super.update(sql, "endereco", novoEndereco, id);
                            System.out.println("Endereço alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o endereço!");
                            e.printStackTrace();
                        }
                    }

                    case 6 -> { //alterando telefone
                        sql = "UPDATE usuario SET telefone = ? WHERE id = ?;";

                        try {
                            System.out.println("Digite seu novo telefone: ");
                            String novoTelefone = scn.nextLine();

                            novoTelefone = validarEntrada(novoTelefone);

                            super.update(sql, "telefone", novoTelefone);
                            System.out.println("Telefone alterado com sucesso!");

                        } catch (SQLException e) {
                            System.out.println("ERRO! Não foi possível alterar o telefone!");
                            e.printStackTrace();
                        }
                    }

                    case 0 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("ERRO! Digite apenas números.");
                scn.nextLine();
            }
        }
    }

    //Desenvolvendo metodo de validação de valores do update (usuario)
    public static String validarEntrada(String entrada) {

        if (entrada.equals("0")) {
            return null;
        }

        Scanner scn = new Scanner(System.in);
        while (entrada == null || entrada.trim().isEmpty()) {
            System.out.print("ERRO! O campo não pode ficar vazio! Digite novamente: ");
            entrada = scn.nextLine();
        }
        return entrada;

    }

    //Desenvolvendo o metodo listar (usuario)
    @Override
    public List<usuario> get() {
        List<usuario> uList = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement ps = super.conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                usuario u = null;

                switch (tipo) {
                    case "idoso" -> u = new idoso(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                    case "familiar" -> u = new familiar(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nasc").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("telefone"),
                            rs.getString("tipo")
                    );
                    case "administrador" -> u = new administrador(
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

                if (u != null) {
                    uList.add(u);
                    System.out.println(u);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO! Não foi possível listar a tabela usuário!");
            e.printStackTrace();
        }
        return uList;
    }

    //Desenvolveno o metoddo para listar por id (usuario)
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

    //Desenvolvendo o metodo deletar (usuario)
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

    //Desenvolvendo o metodo para login (usuario)
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

    //Desenvolvendo o metodo para validar liogin (senha)
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

    //Desenvolvendo o metodo para validar login (email)
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

    public int getIdIdosoByUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT id_idoso FROM idoso WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_idoso"); // retorna o id do idoso
                } else {
                    return -1; // não encontrou
                }
            }
        }
    }

    public idoso getIdosoByUsuarioId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM idoso WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idoso i = new idoso();
                    i.setId(rs.getInt("id_idoso"));
                    i.setNome(rs.getString("nome"));
                    i.setDataNasc(rs.getDate("data_nascimento").toLocalDate());
                    // adiciona o resto dos campos se tiver mais
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar idoso pelo id do usuário!");
            e.printStackTrace();
        }
        return null;
    }

    public familiar getFamiliarByEmail(String email) throws SQLException {
        usuario u = getByEmailAndTipo(email, "familiar");
        if (u instanceof familiar) {
            return (familiar) u;
        }
        return null;
    }

    public idoso getIdosoByEmail(String email) throws SQLException {
        usuario u = getByEmailAndTipo(email, "idoso");
        if (u instanceof idoso) {
            return (idoso) u;
        }
        return null;
    }

    // Busca usuário por email e tipo (ex: para encontrar um familiar específico)
    public usuario getByEmailAndTipo(String email, String tipo) {
        usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND tipo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, tipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String t = rs.getString("tipo");

                switch (t) {
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
            System.out.println("ERRO! Não foi possível buscar o usuário por email e tipo!");
            e.printStackTrace();
        }

        // Se encontrou, preencher também o id específico da tabela de tipo (id_familiar, id_idoso, ...)
        if (usuario != null) {
            int usuarioId = usuario.getId();
            try {
                String tabela = switch (tipo) {
                    case "idoso" -> "idoso";
                    case "familiar" -> "familiar";
                    case "administrador" -> "administrador";
                    default -> null;
                };

                if (tabela != null) {
                    String sql2 = "SELECT * FROM " + tabela + " WHERE id_usuario = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                        ps2.setInt(1, usuarioId);
                        try (ResultSet rs2 = ps2.executeQuery()) {
                            if (rs2.next()) {
                                // o id específico normalmente é a primeira coluna (ex: id_familiar ou id_idoso)
                                int idEspecifico = rs2.getInt(1);
                                switch (tipo) {
                                    case "idoso" -> {
                                        if (usuario instanceof idoso) ((idoso) usuario).setId_idoso(idEspecifico);
                                    }
                                    case "familiar" -> {
                                        if (usuario instanceof familiar) ((familiar) usuario).setId_familiar(idEspecifico);
                                    }
                                    case "administrador" -> {
                                        if (usuario instanceof administrador) ((administrador) usuario).setId_administrador(idEspecifico);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("ERRO! Não foi possível obter o id específico do tipo de usuário: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return usuario;
    }

    // Salva o usuário e retorna o id gerado (útil para associar a outras tabelas)
    public int saveAndReturnId(usuario u) throws SQLException {
        String sqlUsuario = "INSERT INTO usuario(nome, data_nasc, email, senha, endereco, telefone, tipo) VALUES (?, ?, ?, ?, ?, ?, ?);";

        // inserção na tabela 'usuario'
        int idUsuario = super.save(sqlUsuario,
                u.getNome(),
                java.sql.Date.valueOf(u.getDataNasc()),
                u.getE_mail(),
                u.getSenha(),
                u.getEndereco(),
                u.getTelefone(),
                u.getTipo());

        if (idUsuario == -1) throw new SQLException("Não foi possível inserir usuário");

        // inserir na tabela específica e recuperar o id gerado (id_idoso, id_familiar, ...)
        String tabela = switch (u.getTipo()) {
            case "idoso" -> "idoso";
            case "familiar" -> "familiar";
            case "administrador" -> "administrador";
            default -> null;
        };

        if (tabela == null) throw new IllegalArgumentException("Tipo de usuário inválido: " + u.getTipo());

        String sqlTipo = "INSERT INTO " + tabela + " (id_usuario) VALUES (?);";
        int generatedId = -1;
        try (PreparedStatement ps = conn.prepareStatement(sqlTipo, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        }

        // popular o objeto com o id específico, se possível
        if (generatedId != -1) {
            switch (u.getTipo()) {
                case "idoso" -> { if (u instanceof idoso) ((idoso) u).setId_idoso(generatedId); }
                case "familiar" -> { if (u instanceof familiar) ((familiar) u).setId_familiar(generatedId); }
                case "administrador" -> { if (u instanceof administrador) ((administrador) u).setId_administrador(generatedId); }
            }
        }

        // retornar preferencialmente o id da tabela específica (para ser usado em vinculacoes),
        // caso contrário retorna o id do usuário
        return generatedId != -1 ? generatedId : idUsuario;
    }
}