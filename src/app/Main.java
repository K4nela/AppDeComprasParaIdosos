package app;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static view.Menus.menuMain;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static int opcao = 0;
    static usuario Usuario = UsuarioDAO.criarUsuario();

    public static void main(String[] args){
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
                        Scanner scn = new Scanner(System.in);

                        System.out.println("----------Cadastro----------");
                        System.out.println("Nome: ");
                        String nome = scn.nextLine();

                        System.out.println("Data de nascimento (yyyy-MM-dd): ");
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

                        Usuario = new usuario(nome,localData,email,senha,endereco,telefone);

                        usuarioDao.save(Usuario);
                        break;

                    case 2:
                        System.out.println(usuarioDao.get());
                        break;

                    case 3:
                        usuarioDao.update(Usuario);
                        break;

                    case 4:
                        usuarioDao.delete(Usuario);
                        break;
                }
            }while (opcao!=0);



        }catch (Exception e){
            System.out.println("ERRO! Não foi possível rodar o aplicativo!");
        }
    }
}
