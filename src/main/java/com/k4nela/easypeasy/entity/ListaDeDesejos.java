package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "listadedesejos")
public class ListaDeDesejos {

    @Id
    @Column(name = "id_lista")
    private int id;

    @Column(name = "nome_lista")
    private String nomeLista;
    private String descricao;

    @Column (name = "data_criacao")
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_idoso")
    private Idoso idoso;

    // ListaDeDesejos.java
    @OneToMany(mappedBy = "listaDeDesejos") // tem que bater com o atributo de Item
    @JsonManagedReference
    private List<Item> itens;
}
