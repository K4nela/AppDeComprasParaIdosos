package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "familiares")
public class Familiar {
    @Id
    private String idFamiliar;

    @DBRef
    private Usuario usuario;

    public Familiar() {}

    public void setId(String id) {

    }

    // Getters e Setters
}
