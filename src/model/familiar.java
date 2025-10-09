package model;

import javax.xml.crypto.Data;

public class familiar extends usuario{
    private int id_familiar;
    private usuario id_usuario;

    public int getId_familiar() {
        return id_familiar;
    }

    public void setId_familiar(int id_familiar) {
        this.id_familiar = id_familiar;
    }

    public usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public familiar(int id, String nome, Data data_nasc, String e_mail, String senha, String endereco, String telefone, int id_familiar, usuario id_usuario) {
        super(id, nome, data_nasc, e_mail, senha, endereco, telefone);
        this.id_familiar = id_familiar;
        this.id_usuario = id_usuario;
    }
}
