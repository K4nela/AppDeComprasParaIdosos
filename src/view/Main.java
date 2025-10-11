package view;

import dao.Conexao;
import dao.UsuarioDAO;
import model.usuario;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import static view.Menus.menuMain;

public class Main {
    static Scanner scn = new Scanner(System.in);
    static int opcao = 0;

    public static void main(String[] args){
        try{
            //Conecta no banco de dados
            Connection conn = Conexao.createConnectionToMySQL();

            //Instancia o DAO
            UsuarioDAO usuarioDao = new UsuarioDAO(conn);

            //Cria o usuario
            usuario Maria = new usuario(0,"Maria Clara Cruz Passos",
                                        LocalDate.of(2006,03,15),
                                        "maria@gmail.com",
                                        "1234",
                                        "Brazlândia",
                                        "9999-9999");

            do{
                menuMain();
                opcao = scn.nextInt();
                scn.nextLine();

                switch (opcao){
                    case 1:
                        usuarioDao.save(Maria);
                        break;

                    case 2:
                        usuarioDao.get();
                        break;

                    case 3:
                        usuarioDao.update(Maria);
                        break;

                    case 4:
                        usuarioDao.delete(Maria);
                        break;
                }
            }while (opcao!=0);



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
