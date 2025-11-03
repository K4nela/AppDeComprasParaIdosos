package model;

import java.time.LocalDate;

public class idoso extends usuario {
    private int id_idoso;

    public idoso(){}

    public idoso(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        super(nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }

    public idoso(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, int idIdoso, usuario idUsuario) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "idoso");
    }

    public idoso(String nome, String eMail, String senha, String endereco, String telefone) {
    }

    public idoso( int id_idoso, String nome, LocalDate dataNasc, String e_mail, String endereco, String telefone) {
        super(nome, dataNasc, e_mail, endereco, telefone);
        this.id_idoso = id_idoso;
    }

    public int getId_idoso() {
        return id_idoso;
    }

    public void setId_idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }
}
