package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "administradores")
public class Administrador {
    @Id
    private String idAdministrador;

    @DBRef
    private Usuario usuario;

    public Administrador() {}

    public void setId(String id) {
    }

    // Getters e Setters
}
