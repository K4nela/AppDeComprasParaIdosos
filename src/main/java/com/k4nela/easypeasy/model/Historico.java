package com.k4nela.easypeasy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Document(collection = "historicos")
public class Historico {
    @Id
    private String idHistorico;
    private LocalDate dataHistorico;
    private String status; // PENDENTE, EM_ANDAMENTO, CONCLUIDO, CANCELADO

    @DBRef
    private Item item;

    public Historico() {}

    public void setId(String id) {

    }

    // Getters e Setters
}
