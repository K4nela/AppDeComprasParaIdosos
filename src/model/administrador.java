package model;

import javax.xml.crypto.Data;

public class administrador extends usuario{
    private int id_administrador;
    private usuario id_usuario;

    public int getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(int id_administrador) {
        this.id_administrador = id_administrador;
    }

    public usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public administrador(int id, String nome, Data data_nasc, String e_mail, String senha, String endereco, String telefone, int id_administrador, usuario id_usuario) {
        super(id, nome, data_nasc, e_mail, senha, endereco, telefone);
        this.id_administrador = id_administrador;
        this.id_usuario = id_usuario;
    }
}
