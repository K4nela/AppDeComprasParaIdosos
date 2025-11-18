package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "id_lista", nullable = false)
    @JsonIgnoreProperties("itens")
    @JsonBackReference
    private ListaDeDesejos listaDeDesejos;
}
