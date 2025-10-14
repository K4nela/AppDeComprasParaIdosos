package app;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;
import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;
import static view.Menus.menuMain;
import static view.Cadastro.telaCadastro;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static int opcao = 0;

    public static void main(String[] args){
        int id;

        try{
            //Conecta no banco de dados
            Connection conn = Conexao.createConnectionToMySQL();
//
            //Instancia o DAO
            UsuarioDAO UsuarioDao = new UsuarioDAO(conn);

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
                            for(usuario u : UsuarioDao.get()) {
                                System.out.println(u);
                            }

                            }else{
                                System.out.println("Digite seu id para ver suas informações");
                                id = scn.nextInt();
                                scn.nextLine();

                                usuario u = UsuarioDao.getById(id);
                                if (UsuarioDao.getById(id) != null) {
                                            System.out.println(UsuarioDao.getById(id));
                                }else{
                                    System.out.println("Usuário não encontrado!");
                                }
                            }
                        break;

                    case 3:
                        System.out.println("Digite seu id para atualizar suas informações");
                        id = scn.nextInt();
                        scn.nextLine();

                        if (UsuarioDao.getById(id) != null) {
                            System.out.println(UsuarioDao.getById(id));
                        }

                        UsuarioDao.update(id);
                        break;

                    case 4:
                            System.out.println("Digite seu id para deletar o usuário");
                            id = scn.nextInt();
                            scn.nextLine();

                            UsuarioDao.delete(id);

                        break;
                }
            }while (opcao!=0);



        }catch (Exception e){
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
        }
    }
}
