package controller;

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

public final class CadastroController {

    /*
    Desenvolvendo tela de cadastro de usuários com seus métodos correspondentes
     */
    public static void telaCadastro() throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO UsuarioDao = new UsuarioDAO(conn);
        int tipo = -1;
        LocalDate localData = null;

        while (true) {

            System.out.println("----------Cadastro----------");
            menuTipoUsuario();

            while (true) {
                System.out.print("Tipo: ");
                String tipoStr = scn.nextLine();

                try {
                    tipo = Integer.parseInt(tipoStr);

                    if (tipo == 0) {
                        System.out.println("Voltando...");
                        return;
                    }

                    if (tipo >= 1 && tipo <= 3) {
                        break;

                    } else {
                        System.out.println("ERRO! Tipo de usuário inválido. Escolha entre 1, 2 ou 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERRO! Digite apenas números. Escolha entre 1, 2 ou 3.");
                }
            }


            System.out.printf("Nome: ");
            String nome = scn.nextLine();


            while (true) {
                try {
                    System.out.println("Data de nascimento (dd/MM/yyyy): ");
                    String data = scn.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    localData = LocalDate.parse(data, formatter);
                    break;
                } catch (Exception e) {
                    System.out.println("ERRO! Data inválida, insira no formato dd/MM/yyyy.");
                }
            }

            System.out.printf("email: ");
            String email = scn.nextLine();

            System.out.printf("senha: ");
            String senha = scn.nextLine();

            System.out.printf("endereço: ");
            String endereco = scn.nextLine();

            System.out.printf("telefone: ");
            String telefone = scn.nextLine();

            System.out.println("---------------------------");

            usuario u = switch (tipo) {
                case 1 -> new idoso(nome, localData, email, senha, endereco, telefone);
                case 2 -> new familiar(nome, localData, email, senha, endereco, telefone);
                case 3 -> new administrador(nome, localData, email, senha, endereco, telefone);

                default -> throw new IllegalArgumentException("Tipo de usuário inválido!");
            };

            try {
                UsuarioDao.save(u);
                System.out.println("Usuário cadastrado com sucesso!");
                return;

            } catch (Exception e) {
                System.out.println("ERRO! Não foi possível cadastrar o usuário. Tente novamente.");
                e.printStackTrace();
            }
        }
    }
}
