package controller;

import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.Listas.exibirUsuario;
import static view.Menus.menuPerfil;
import static view.Listas.telaUsuarios;

public final class LoginController {
    static Scanner scn = new Scanner(System.in);

    /*
    Exibe a tela de login, valida se o email e senha estão no bamco de dados
     */
    public static usuario telaLogin(Connection conn) throws Exception {
        //instancia UsuarioDAO para usar funções de busca no banco de dados
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);

        //instanciamcia e inicializa variáveis de usuários que serão úteis
        usuario usuario = null;
        usuario usuarioSenha;
        usuario usuarioEmail;

        //loop continua até o login ser bem sucedido ou o usuário decidir sair
        while (usuario == null) {


            System.out.println("Digite [0] para voltar.");
            System.out.println("--------Tela De Login--------");
            System.out.print("email: ");
            String email = scn.nextLine();

            //opção para sair do loop (opção padrão de tratamento de opção de saída)
            if (email.equals("0")) {
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            System.out.print("Senha: ");
            String senha = scn.nextLine();

            if (senha.equals("0")) {
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            //instancias para tratamento de erro
            usuarioEmail = usuarioDao.getByEmail(email); //busca usuário cadastrado através do email
            usuarioSenha = usuarioDao.getBySenha(senha); //busca usuário cadastrado através da senha
            usuario = usuarioDao.login(email, senha); //busca usuário cadastrado através do email e senha

            //caso o email e a senha exista no banco de dados o metodo retorna o usuário
            if (usuarioEmail != null && usuarioSenha != null) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem vindo, " + usuario.getNome() + ", " + usuario.getTipo() + "!");
                System.out.println("-----------------------------");
                return usuario;

                //erro caso um usuário com esteja cadastrado no banco de dados
            } else if (usuarioEmail == null && usuarioSenha == null) {
                System.out.println("ERRO! Usuário não cadastrado.");
                System.out.println("-----------------------------");

                //errp caso não exista um usuário com essa senha no banco de dados
            } else if (usuarioEmail != null && usuarioSenha == null) {
                System.out.println("ERRO! Senha incorreta.");
                System.out.println("-----------------------------");

                //errp para caso não exista um usuário com esse email no banco de dados
            } else {
                System.out.println("ERRO! Email incorreto.");
                System.out.println("-----------------------------");
            }
        }
        return usuario;
    }

    /*
    acessa perfil do usuário logado e da opções para gerenciar sua conta.
     */
    public static void verPerfil(usuario u, Connection conn) {
        UsuarioDAO UsuarioDAO = new UsuarioDAO(conn);
        int id = u.getId();
        int opcao = -1;

        while (true) {
            try {
                menuPerfil();
                opcao = scn.nextInt();

                switch (opcao) {//opções de menu
                    case 1 -> exibirUsuario(UsuarioDAO, u); //mostra os dados do usuário (metodo da view -> Listas)
                    case 2 -> UsuarioDAO.update(id); //opção para atualizar os dados pessoais do usuário
                    case 3 -> {
                        UsuarioDAO.delete(id); //deleta o usuário e volta para a tela de login;
                        return;
                    }
                    case 0 -> { //volta para a home
                        System.out.println("Voltando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }

                //erro, trata entradas que não são numéricas
            } catch (InputMismatchException e) {
                System.out.println("ERRO! Digite apenas números");
                scn.nextLine();
            }
        }
    }
}
