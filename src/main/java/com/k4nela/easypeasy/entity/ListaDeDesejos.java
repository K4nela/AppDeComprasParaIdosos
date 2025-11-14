package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

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
}
