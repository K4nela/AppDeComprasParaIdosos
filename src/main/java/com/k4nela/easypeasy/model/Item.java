package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "itens")
public class Item {
    @Id
    private String idItem;
    private String nomeItem;
    private String descricao;
    private int quantidade;
    private String nomeLoja;
    private String link;

    @DBRef
    private ListaDeDesejos lista;

    public Item() {}

    public void setId(String id) {

    }

    // Getters e Setters
}
