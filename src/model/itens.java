package model;

public class itens {
    private int id_iten;
    private listaDeCompras id_lista;
    private String nome_iten;
    private String descricao;
    private int quantidade;
    private String nome_loja;
    private String link;

    public itens() {
    }

    public itens(int id_iten, listaDeCompras id_lista, String nome_iten, String descricao, int quantidade, String nome_loja, String link) {
        this.id_iten = id_iten;
        this.id_lista = id_lista;
        this.nome_iten = nome_iten;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.nome_loja = nome_loja;
        this.link = link;
    }

    public int getId_iten() {
        return id_iten;
    }
    public void setId_iten(int id_iten) {
        this.id_iten = id_iten;
    }

    public listaDeCompras getId_lista() {
        return id_lista;
    }
    public void setId_lista(listaDeCompras id_lista) {
        this.id_lista = id_lista;
    }

    public String getNome_iten() {
        return nome_iten;
    }
    public void setNome_iten(String nome_iten) {
        this.nome_iten = nome_iten;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome_loja() {
        return nome_loja;
    }
    public void setNome_loja(String nome_loja) {
        this.nome_loja = nome_loja;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "\n------- Item -------" +
                "\nID do item: " + id_iten +
                "\nLista vinculada: " + (id_lista != null ? id_lista.getId_lista() : "Nenhuma") +
                "\nNome do item: " + nome_iten +
                "\nDescrição: " + descricao +
                "\nQuantidade: " + quantidade +
                "\nNome da loja: " + nome_loja +
                "\nLink: " + link +
                "\n--------------------";
    }
}
