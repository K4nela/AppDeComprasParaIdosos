package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class listaDeCompras extends idoso{
    private int id_lista;
    private idoso id_idoso;
    private String nomeLista;
    private LocalDate dataCriacao;
    private String descricao;
    private List<String> itens;

    public listaDeCompras() {
    }

    public listaDeCompras(int id_lista) {
        this.id_lista = id_lista;
    }

    public listaDeCompras(int id, String nome, LocalDate dataNasc, String e_mail, String senha, String endereco, String telefone, int id_idoso, usuario id_usuario, int id_lista, idoso id_idoso1) {
        super(id, nome, dataNasc, e_mail, senha, endereco, telefone, id_idoso, id_usuario);
        this.id_lista = id_lista;
        this.id_idoso = id_idoso1;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.itens = new ArrayList<>();

    }

    public listaDeCompras(String nome, String e_mail, String senha, String endereco, String telefone, String nomeLista, LocalDate dataCriacao, String descricao, List<String> itens) {
        super(nome, e_mail, senha, endereco, telefone);
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "\n------- Lista de Compras -------" +
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

    public void setId_idoso(idoso id_idoso) {
        this.id_idoso = id_idoso;
    }

    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<String> getItens() { return itens; }
    public void setItens(List<String> itens) { this.itens = itens; }

    public void adicionarItem(String item) {
        this.itens.add(item);
    }

    public void removerItem(String item) {
        this.itens.remove(item);
    }

}
