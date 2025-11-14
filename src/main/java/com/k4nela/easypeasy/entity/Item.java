package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor

@Document(collection = "itens")
public class Item {
    @Id
    private String idItem;
    private String nomeItem;
    private String descricao;
    private int quantidade;
    private String nomeLoja;
    private String link;

    @DBRef
    private ListaDeDesejos lista;
}
