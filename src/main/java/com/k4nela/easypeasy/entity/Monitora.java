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

@Document(collection = "monitora")
public class Monitora {

    @Id
    private String id;

    @DBRef
    private Idoso idoso;  // referência ao idoso monitorado

    @DBRef
    private Familiar familiar;  // referência ao familiar que monitora

    private String dataInicio;
    private String observacoes;
}
