package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "listasDeDesejos")
public class ListaDeDesejos {
    @Id
    private String idLista;
    private String nomeLista;
    private String descricao;
    private LocalDate dataCriacao;

    @DBRef
    private Idoso idoso;

    @DBRef
    private List<Item> itens;

    public ListaDeDesejos() {}

    public void setId(String id) {

    }

    // Getters e Setters
}
