package model;

import java.time.LocalDate;

public class Idoso extends usuario{
    private int id_idoso;
    private usuario id_usuario;

    public Idoso() {
    }

    public Idoso(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, int id_idoso, usuario id_usuario) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone);
        this.id_idoso = id_idoso;
        this.id_usuario = id_usuario;
    }

    public Idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }
}
