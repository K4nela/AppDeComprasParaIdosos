package model;

import javax.xml.crypto.Data;

public class listaDeCompras extends Idoso{
    private int id_lista;
    private Idoso id_idoso;

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public Idoso getId_idoso() {
        return id_idoso;
    }

    public void setId_idoso(Idoso id_idoso) {
        this.id_idoso = id_idoso;
    }

    public listaDeCompras(int id, String nome, Data data_nasc, String e_mail, String senha, String endereco, String telefone, int id_idoso, usuario id_usuario, int id_lista, Idoso id_idoso1) {
        super(id, nome, data_nasc, e_mail, senha, endereco, telefone, id_idoso, id_usuario);
        this.id_lista = id_lista;
        this.id_idoso = id_idoso1;


    }
}
