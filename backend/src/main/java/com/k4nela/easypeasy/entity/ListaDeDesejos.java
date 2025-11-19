package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @CreationTimestamp
    @Column (name = "data_criacao",  updatable = false, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_idoso")
    @JsonIgnoreProperties("desejos")
    private Idoso idoso;

    // ListaDeDesejos.java
    @OneToMany(mappedBy = "listaDeDesejos", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> itens = new ArrayList<>();


}
