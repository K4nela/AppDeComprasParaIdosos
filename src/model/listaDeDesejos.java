package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class listaDeDesejos{
    private int id_lista;
    private int id_idoso;
    private String nomeLista;
    private LocalDate dataCriacao;
    private String descricao;
    private List<item> item;

    public listaDeDesejos() {
        this.item = new ArrayList<>();
    }

    public listaDeDesejos(int id_idoso, String descricao, String nomeLista) {
        this.id_idoso = id_idoso;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.nomeLista = nomeLista;
    }

    public listaDeDesejos(int id_idoso, String nomeLista, LocalDate dataCriacao, String descricao) {
        this.id_idoso = id_idoso;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
    }

    public listaDeDesejos(item itens) {
        this.item.add((item) item);
    }

    @Override
    public String toString() {
        return  "\n------- Lista de Desejos -------" +
                "\nid: " + id_lista +
                "\nNome da lista: " + nomeLista +
                "\nData de criação: " + dataCriacao +
                "\nObservações: " + descricao +
                "\n-------------------------------" +
                "\n" + item;
    }

    public int getId_lista() {
        return id_lista;
    }
    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public int getId_idoso(){return id_idoso;}
    public void setId_idoso(int id_idoso) {
        this.id_idoso = id_idoso;
    }

    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<item> getItens() { return item; }
    public void setItens(List<item> item) { this.item = item; }

    public void adicionarItem(item item) {
        this.item.add(item);
    }

    public void removerItem(item item) {
        this.item.remove(item);
    }



}
