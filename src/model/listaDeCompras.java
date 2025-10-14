package model;

import java.util.List;

public class listaDeCompras{
    private int id_lista;
    private int id_idoso;
    private List<itens> itens;

    public listaDeCompras(int id_lista, int id_idoso, List<itens> itens) {
        this.id_lista = id_lista;
        this.id_idoso = id_idoso;
        this.itens = itens;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }
}
