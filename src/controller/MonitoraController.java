package controller;

import dao.MonitoraDAO;
import dao.UsuarioDAO;
import model.familiar;
import model.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static view.Listas.telaUsuarios;

/*
 Controla a associação entre idosos e familiares (monitoramento).
 Garante que apenas familiares cadastrados possam acompanhar idosos.
 */
public final class MonitoraController extends MonitoraDAO {
    static Scanner scn = new Scanner(System.in);

    public MonitoraController(Connection conn) {
        super(conn);
    }

    /*
    Lista os familiares associados ao idoso logado
     */
    static void listarFamiliares(usuario u, Connection conn) {
        try {
            MonitoraDAO monitoraDao = new MonitoraDAO(conn); //instancia DAO para buscar associações
            List<usuario> familia = monitoraDao.getByFm(u.getId()); //Busca familiares pelo ID do idoso

            if (familia.isEmpty()) {
                System.out.println("ERRO! Nenhum familiar associado a este idoso.");
                return;
            }
            telaUsuarios(familia); //exibe lista de familiares (metodo do view -> Listas)

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível listar familiares");
            e.getMessage();
        }
    }

    /*
    Lista os idosos associados ao familiar logado e oferece opcao de vincular novo idoso
     */
    static void listarIdosos(usuario u, Connection conn) {

        try {
            MonitoraDAO monitoraDao = new MonitoraDAO(conn);//instancia DAO para buscar associações
            List<usuario> idosos = monitoraDao.getById(u.getId()); //Busca idosos pelo ID do familiare

            if (idosos.isEmpty()) {
                System.out.println("ERRO! Nenhum idoso associado a este familiar.");

            } else {//
                telaUsuarios(idosos); //Exibe idosos já vinculados (metodo do view -> Listas)
            }

            associar(u, conn); //Oferece opção de vincular novo idoso

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Operação cancelada.");

        } catch (Exception e) {
            System.out.println("ERRO! Não foi possível listar ou vincular idosos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    associa idoso ao familiar usando metodo associarFamiliarEIdoso
     */
    public static void associar(usuario u, Connection conn) throws SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO(conn);
        usuario idosoEncontrado = null;
        String email;

        String u_email = u.getE_mail();
        familiar f = usuarioDao.getFamiliarByEmail(u_email); // Oferece opção de vincular novo idoso


        System.out.println("Digite [0] para sair;");
        System.out.println("1 - Vincular mais idosos");
        System.out.printf("Escolha uma opção: ");

        int opcao = scn.nextInt();
        scn.nextLine();

        if (opcao == 0) {
            System.out.println("Voltando...");
            return;
        }

        switch (opcao) {
            case 1 -> {
                while (true) {
                    try{
                        System.out.println("Digite [0] para sair;");
                        System.out.print("Digite o email do idoso a vincular a sua conta: ");
                        email = scn.nextLine();

                        idosoEncontrado = usuarioDao.getIdosoByEmail(email); // Busca idoso pelo email

                        if (email.equals("0")) {
                            return;
                        }

                        if (idosoEncontrado == null) {
                            System.out.println("ERRO! Familiar não encontrado. Cadastre o familiar antes ou informe um email válido.");
                        }
                        try {

                            associarFamiliarEIdoso(conn, f, idosoEncontrado); //metodo que faz a associação

                        } catch (Exception e) {
                            System.out.println("ERRO! Não foi associar idoso ao familiar.");
                            e.printStackTrace();
                        }

                        //tratamento de erro para entradas inválidas
                    }catch (InputMismatchException e){
                        System.out.println("ERRO! Digite apenas números");
                        scn.nextLine();
                    }
                }
            }
        }


    }

    /*
    associa idoso ao familiar acessando o id atraves de usuarios instanciados
     */
    public static void associarFamiliarEIdoso(Connection conn, usuario familiar, usuario idoso) throws Exception {
        dao.MonitoraDAO monitoraDAO = new dao.MonitoraDAO(conn);

        // pega os ids específicos, não o id genérico
        int idFamiliarPk = (familiar instanceof model.familiar) ? ((model.familiar) familiar).getId_familiar() : familiar.getId();
        int idIdosoPk = (idoso instanceof model.idoso) ? ((model.idoso) idoso).getId_idoso() : idoso.getId();

        // verifica se os ids são válidos
        if (idFamiliarPk == 0 || idIdosoPk == 0) {
            throw new Exception("Erro: id do familiar ou id do idoso inválido!");
        }
        // faz a associação
        monitoraDAO.vincular(idFamiliarPk, idIdosoPk);
    }

}
