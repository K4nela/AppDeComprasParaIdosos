package model;

import java.time.LocalDate;

public class familiar extends usuario{
    private int id_familiar;
    private int id_usuario;

    public familiar(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, String tipo) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, "familiar");
    }

    public familiar(String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone) {
        super(nome, dataNasc, e_mail, senha, endereco, telefone, "familiar");
    }

    @Override
    public String toString() {
        super.toString();
        return "familiar{" +
                "id_familiar=" + id_familiar +
                ", id_usuario=" + id_usuario +
                '}';
    }

    public int getId_familiar() {
        return id_familiar;
    }

    public void setId_familiar(int id_familiar) {
        this.id_familiar = id_familiar;
    }
}
