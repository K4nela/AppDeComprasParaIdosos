package view;

import dao.Conexao;
import model.administrador;
import model.familiar;
import model.idoso;
import model.usuario;
import dao.UsuarioDAO;


import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static view.Menus.menuTipoUsuario;

public abstract class Cadastro{

    public static void telaCadastro() throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO UsuarioDao = new UsuarioDAO(conn);


        System.out.println("----------Cadastro----------");
        menuTipoUsuario();

        System.out.println("Tipo: ");
        int tipo = scn.nextInt();
        scn.nextLine();

        System.out.println("Nome: ");
        String nome = scn.nextLine();

        System.out.println("Data de nascimento (dd/MM/yyyy): ");
        String data = scn.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localData = LocalDate.parse(data, formatter);

        System.out.println("email: ");
        String email = scn.nextLine();

        System.out.println("senha: ");
        String senha = scn.nextLine();

        System.out.println("endereço: ");
        String endereco = scn.nextLine();

        System.out.println("telefone: ");
        String telefone = scn.nextLine();


        System.out.println("---------------------------");

        usuario u = switch (tipo) {
            case 1 -> new idoso(nome, localData, email, senha, endereco, telefone);
            case 2 -> new familiar(nome, localData, email, senha, endereco, telefone);
            case 3 -> new administrador(nome, localData, email, senha, endereco, telefone);
            default -> throw new IllegalArgumentException("Tipo de usuário inválido!");
        };

        UsuarioDao.save(u);
        System.out.println("Usuário cadastrado com sucesso!");
    }
}
