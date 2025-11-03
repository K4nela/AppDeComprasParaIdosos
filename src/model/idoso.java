package model;

import java.time.LocalDate;

public class idoso extends usuario {
    private int id_idoso;
    private int id_usuario;


    public idoso(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        super(nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(){
        super("",LocalDate.of(0000,00,00),"","","","","idoso");
    }

    public idoso(int idIdoso) {
        this.id_idoso = idIdoso;
    }

    public idoso(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, int idIdoso, usuario idUsuario) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(String nome, String eMail, String senha, String endereco, String telefone) {
    }

    public int getId_idoso() {
        return id_idoso;
    }

    public void setId_idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }
}
