package model;

import java.util.ArrayList;
import java.util.List;

public class item {
    private int id_item;
    private int id_lista;
    private String nome_item;
    private String descricao;
    private int quantidade;
    private String nome_loja;
    private String link;
    private status status;
    private List<historico> historicos = new ArrayList<>();

    public item() {
    }

    public item(List<historico> historicos) {
        this.historicos = historicos;
    }

    public item(int id_lista, String nome_item, String descricao, int quantidade, String nome_loja, String link) {
        this.id_lista = id_lista;
        this.nome_item = nome_item;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.nome_loja = nome_loja;
        this.link = link;
    }

    public item(int id_item, int id_lista, String nome_item, String descricao, int quantidade, String nome_loja, String link) {
        this.id_item = id_item;
        this.id_lista = id_lista;
        this.nome_item = nome_item;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.nome_loja = nome_loja;
        this.link = link;
    }

    public item(String nome_item, String descricao, int quantidade, String nome_loja, String link, status status) {
        this.nome_item = nome_item;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.nome_loja = nome_loja;
        this.link = link;
        this.status = status;
    }

    // Getters e Setters
    public int getId_item() { return id_item; }
    public void setId_item(int id_item) { this.id_item = id_item; }

    public int getId_lista() { return id_lista; }
    public void setId_lista(int id_lista) { this.id_lista = id_lista; }

    public String getNome_item() { return nome_item; }
    public void setNome_item(String nome_item) { this.nome_item = nome_item; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getNome_loja() { return nome_loja; }
    public void setNome_loja(String nome_loja) { this.nome_loja = nome_loja; }

    public String getLink() { return this.link; }
    public void setLink(String link) { this.link = link; }

    public status getStatus() {
        return status;
    }
    public void setStatus(status status) {
        this.status = status;
    }

    public List<historico> getHistoricos() {
        return historicos;
    }
    public void setHistoricos(historico h) {
        this.historicos.add(h);
    }

    public void addHistorico(historico h) {
        this.historicos.add(h);
    }
    public void setHistoricos(List<historico> historicos) {
        this.historicos = historicos;
    }
}
