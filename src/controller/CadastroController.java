package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import model.administrador;
import model.familiar;
import model.idoso;
import model.usuario;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static view.Menus.menuTipoUsuario;

/*
 Controla o cadastro completo de usuários (idoso, familiar, administrador)
*/
public final class CadastroController {
    static Scanner scn = new Scanner(System.in);

    /*
     Tela principal de cadastro
    */
    public static void telaCadastro() throws Exception {
        // Conexão única com o banco (reutilizada)
        Connection conn = Conexao.createConnectionToMySQL();

        // DAO para operações em usuário e herança
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        int tipo = -1;
        LocalDate localData;

        while (true) {
            System.out.println("----------Cadastro----------");
            menuTipoUsuario(); // exibe o menu para selecionar o tipo de usuário que será cadastrado (metodo da view -> menus)

            // === VALIDA TIPO DE USUÁRIO ===
            while (true) {
                System.out.print("Tipo: ");
                tipo = scn.nextInt();
                scn.nextLine();

                try {

                    if (tipo == 0) {
                        System.out.println("Voltando...");
                        return; // Sai do cadastro
                    }

                    if (tipo >= 1 && tipo <= 3) {
                        break; // Tipo válido
                    } else {
                        System.out.println("ERRO! Tipo de usuário inválido. Escolha entre 1, 2 ou 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERRO! Digite apenas números. Escolha entre 1, 2 ou 3.");
                }
            }

            // === COLETA DADOS COMUNS ===
            System.out.print("Nome: ");
            String nome = scn.nextLine();

            // Validação de data com formato dd/MM/yyyy
            while (true) {
                try {
                    System.out.print("Data de nascimento (dd/MM/yyyy): ");
                    String data = scn.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    localData = LocalDate.parse(data, formatter);
                    break;
                } catch (Exception e) {
                    System.out.println("ERRO! Data inválida, insira no formato dd/MM/yyyy.");
                }
            }

            System.out.print("email: ");
            String email = scn.nextLine();

            System.out.print("senha: ");
            String senha = scn.nextLine();

            System.out.print("endereço: ");
            String endereco = scn.nextLine();

            System.out.print("telefone: ");
            String telefone = scn.nextLine();

            System.out.println("---------------------------");

            usuario u;

            // === CADASTRO POR TIPO ===


            switch (tipo) {
                // IDOSO
                case 1 -> {
                    String emailFamiliar;
                    usuario familiarEncontrado = null;

                    // Validação: familiar deve existir
                    do {
                        System.out.print("Email do familiar responsável: ");
                        emailFamiliar = scn.nextLine();
                        familiarEncontrado = usuarioDao.getByEmailAndTipo(emailFamiliar, "familiar");

                        if (familiarEncontrado == null) {
                            System.out.println("ERRO! Familiar não encontrado, informe um email válido.");
                        }
                    } while (familiarEncontrado == null);

                    //instancia um usuario com os dados inseridos para poder salva-lo no banco de dados
                    u = new idoso(nome, localData, email, senha, endereco, telefone);

                    try {
                        //Salva usuário base (metodo da dao -> UsuarioDAO)
                        usuarioDao.save(u);
                        //Associa ao familiar (metodo do controller -> MonitroaController)
                        MonitoraController.associarFamiliarEIdoso(conn, familiarEncontrado, u);

                        System.out.println("Idoso cadastrado e associado ao familiar com sucesso!");
                        return; // Sai após sucesso
                    } catch (Exception e) {
                        System.out.println("ERRO! Não foi possível cadastrar o idoso ou associar ao familiar. Tente novamente.");
                        e.printStackTrace();
                    }
                }
                // FAMILIAR
                case 2 -> {
                    //instancia um usuario com os dados inseridos para poder salva-lo no banco de dados
                    u = new familiar(nome, localData, email, senha, endereco, telefone);
                    try {
                        //Salva usuário base (metodo da dao -> UsuarioDAO)
                        usuarioDao.save(u);
                        System.out.println("Familiar cadastrado com sucesso!");
                        return;
                    } catch (Exception e) {
                        System.out.println("ERRO! Não foi possível cadastrar o familiar. Tente novamente.");
                        e.printStackTrace();
                    }
                }
                // ADMINISTRADOR
                case 3 -> {
                    //instancia um usuario com os dados inseridos para poder salva-lo no banco de dados
                    u = new administrador(nome, localData, email, senha, endereco, telefone);
                    try {
                        //Salva usuário base (metodo da dao -> UsuarioDAO)
                        usuarioDao.save(u);
                        System.out.println("Administrador cadastrado com sucesso!");
                        return;
                    } catch (Exception e) {
                        System.out.println("ERRO! Não foi possível cadastrar o administrador. Tente novamente.");
                        e.printStackTrace();
                    }
                }

                default -> System.out.println("Tipo de usuário inválido!");
            }
        }
    }
}