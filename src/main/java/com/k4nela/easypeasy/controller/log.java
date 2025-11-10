package com.k4nela.easypeasy.controller;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")
public class log {
    @Id
    private String id;
    private String usuario;
    private String acao;
    private String dataHora;

    // getters e setters
}
