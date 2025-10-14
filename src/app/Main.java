package app;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static view.Menus.menuMain;
import static view.Cadastro.telaCadastro;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static int opcao = 0;
    static usuario Usuario = UsuarioDAO.criarUsuario();

    public static void main(String[] args){
        int id;
        try{
            //Conecta no banco de dados
            Connection conn = Conexao.createConnectionToMySQL();

            //Instancia o DAO
            UsuarioDAO usuarioDao = new UsuarioDAO(conn);

            do{
                menuMain();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao){
                    case 1:
                        telaCadastro();
                        break;

                    case 2:
                        System.out.println("Deseja ver a lista completa?");
                        System.out.println("[Y/N]");
                        String opcao = scn.nextLine();

                        if(Objects.equals(opcao, "Y")){
                                System.out.println(usuarioDao.get());

                            }else if(Usuario != null) {
                                System.out.println("Digite seu id para ver suas informações");
                                id = scn.nextInt();
                                scn.nextLine();

                                if (usuarioDao.getById(id) != null) {
                                    System.out.println(usuarioDao.getById(id));
                                }
                            }
                        break;

                    case 3:
                        System.out.println("Digite seu id para atualizar suas informações");
                        id = scn.nextInt();
                        scn.nextLine();

                        if (usuarioDao.getById(id) != null) {
                            System.out.println(usuarioDao.getById(id));
                        }

                        usuarioDao.update(id);
                        break;

                    case 4:
                            System.out.println("Digite seu id para deletar o usuário");
                            id = scn.nextInt();
                            scn.nextLine();

                            usuarioDao.delete(id);

                        break;
                }
            }while (opcao!=0);



        }catch (Exception e){
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
        }
    }
}
