package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class listaDeCompras extends Idoso{
    private int id_lista;
    private Idoso id_idoso;
    private String nomeLista;
    private LocalDate dataCriacao;
    private String observacoes;
    private List<String> itens;

     //  Construtor vazio
    public listaDeCompras() {
    }

    public listaDeCompras(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, int id_idoso, usuario id_usuario, int id_lista, Idoso id_idoso1) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, id_idoso, id_usuario);
        this.id_lista = id_lista;
        this.id_idoso = id_idoso1;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.observacoes = observacoes;
        this.itens = new ArrayList<>();

    }

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

    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public List<String> getItens() { return itens; }
    public void setItens(List<String> itens) { this.itens = itens; }

    public void adicionarItem(String item) {
        this.itens.add(item);
    }

    public void removerItem(String item) {
        this.itens.remove(item);
    }

    @Override
    public String toString() {
        return "\n------- Lista de Compras -------" +
                "\nID da lista: " + id_lista +
                "\nNome da lista: " + nomeLista +
                "\nData de criação: " + dataCriacao +
                "\nObservações: " + observacoes +
                "\nItens: " + itens +
                "\n-------------------------------";
    }

}
