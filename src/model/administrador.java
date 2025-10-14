package model;

import java.time.LocalDate;

public class administrador extends usuario{
    private int id_administrador;
    private int id_usuario;

    public administrador(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "administrador");
    }

    public administrador(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        super(nome, dataNasc, e_mail, senha, endereco, telefone, "administrador");
    }

    @Override
    public String toString() {
        super.toString();
        return "administrador{" +
                "id_administrador=" + id_administrador +
                ", id_usuario=" + id_usuario +
                '}';
    }

    public int getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(int id_administrador) {
        this.id_administrador = id_administrador;
    }
}
