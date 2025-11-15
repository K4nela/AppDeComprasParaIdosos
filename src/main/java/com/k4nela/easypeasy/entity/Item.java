package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id_item")
    private int id;

    @Column(name = "nome_item")
    private String nomeItem;

    private String descricao;
    private int quantidade;

    @Column(name = "nome_loja")
    private String nomeLoja;
    private String link;


    // Item.java
    @ManyToOne
    @JoinColumn(name = "id_lista")
    @JsonBackReference
    private ListaDeDesejos listaDeDesejos; // o nome precisa bater com mappedBy
}
