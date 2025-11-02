package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class listaDeDesejos{
    private int id_lista;
    private idoso id_idoso;
    private String nomeLista;
    private LocalDate dataCriacao;
    private String descricao;
    private List<itens> itens;

    public listaDeDesejos() {
        this.itens = new ArrayList<>();
    }

    public listaDeDesejos(String descricao, String nomeLista) {
        this.descricao = descricao;
        this.nomeLista = nomeLista;
    }

    public listaDeDesejos(int id_lista, idoso id_idoso, String nomeLista, LocalDate dataCriacao, String descricao, List<itens> itens) {
        this.id_lista = id_lista;
        this.id_idoso = id_idoso;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "\n------- Lista de Desejos -------" +
                "\nID da lista: " + id_lista +
                "\nNome da lista: " + nomeLista +
                "\nData de criação: " + dataCriacao +
                "\nObservações: " + descricao +
                "\nItens: " + itens +
                "\n-------------------------------";
    }

    public int getId_lista() {
        return id_lista;
    }
    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public idoso getId_idoso(){return id_idoso;}
    public void setId_idoso(idoso id_idoso) {
        this.id_idoso = id_idoso;
    }

    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<itens> getItens() { return itens; }
    public void setItens(List<itens> itens) { this.itens = itens; }

    public void adicionarItem(itens item) {
        this.itens.add(item);
    }

    public void removerItem(itens item) {
        this.itens.remove(item);
    }

}
