package model;

import java.util.List;

public class listaDeCompras{
    private int id_lista;
    private int id_idoso;
    private List<itens> itens;
    private historico historico;

    public listaDeCompras(int id_lista, int id_idoso, List<itens> itens, historico historico) {
        this.id_lista = id_lista;
        this.id_idoso = id_idoso;
        this.itens = itens;
        this.historico = historico;
    }

    public listaDeCompras(int id_lista, int id_idoso){
        this.id_lista = id_lista;
        this.id_idoso = id_idoso;
    }


    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }
}
