package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.Menus.menuPerfil;

public final class LoginController {

    /*
    Desenvolvendo função para realizar login no sistema através do email e senha.
     */
    public static usuario telaLogin() throws Exception {
        Scanner scn = new Scanner(System.in);
        Connection conn = Conexao.createConnectionToMySQL();
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        usuario usuario = null;
        usuario usuarioSenha = null;
        usuario usuarioEmail = null;

        while (usuario == null) {

            System.out.println("Digite [0] para voltar.");
            System.out.println("--------Tela De Login--------");
            System.out.print("email: ");
            String email = scn.nextLine();

            if(email.equals("0")){
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            System.out.print("Senha: ");
            String senha = scn.nextLine();

            if(senha.equals("0")){
                System.out.println("Voltando...");
                System.out.println("-----------------------------");
                return null;
            }

            usuarioEmail = usuarioDao.getByEmail(email);
            usuarioSenha = usuarioDao.getBySenha(senha);
            usuario = usuarioDao.login(email, senha);

            if (usuarioEmail != null && usuarioSenha != null) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem vindo, " + usuario.getNome() + ", " + usuario.getTipo() + "!");
                System.out.println("-----------------------------");
                return usuario;

            }else if( usuarioEmail == null && usuarioSenha == null) {
                System.out.println("ERRO! Email não cadastrado.");
                System.out.println("-----------------------------");

            } else if ( usuarioEmail != null && usuarioSenha == null) {
                System.out.println("ERRO! Senha incorreta.");
                System.out.println("-----------------------------");

            }else{
                System.out.println("ERRO! Email incorreto.");
                System.out.println("-----------------------------");
            }
        }
        return usuario;
    }

    /*
    Desenvolvendo função para ver perfil do usuário logado
     */
    public static usuario verPerfil(usuario u, Connection conn){
            Scanner scn = new Scanner(System.in);
            UsuarioDAO UsuarioDAO = new UsuarioDAO(conn);
            int id = u.getId();
            int opcao = -1;

            while (true){
                try{
                    menuPerfil();
                    opcao = scn.nextInt();

                    switch (opcao){
                        case 1 -> System.out.println(UsuarioDAO.getById(id));
                        case 2 -> UsuarioDAO.update(id);
                        case 3 -> {
                            UsuarioDAO.delete(id);
                            u = null;
                            return u;
                        }
                        case 0 -> {
                            System.out.println("Voltando...");
                            return u;
                        }
                        default -> System.out.println("Opção inválida!");
                    }

                }catch (InputMismatchException e){
                    System.out.println("ERRO! Digite apenas números");
                    scn.nextLine();
                }

            }
        }
}
