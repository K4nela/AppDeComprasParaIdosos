package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "idosos")
public class Idoso {
    @Id
    private String idIdoso;

    @DBRef
    private Usuario usuario;

    public Idoso() {}

    public void setId(String id) {

    }

    // Getters e Setters
}
