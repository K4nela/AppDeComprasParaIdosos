package view;

import dao.Conexao;
import model.usuario;
import dao.UsuarioDAO;


import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Cadastro {
    public static void telaCadastro() throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO UsuarioDao = new UsuarioDAO(conn);


        System.out.println("----------Cadastro----------");
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

        usuario usuario = new usuario(nome, localData, email, senha, endereco, telefone);

        UsuarioDao.save(usuario);
    }
}
