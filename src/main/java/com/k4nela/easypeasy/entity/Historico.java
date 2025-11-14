package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Data
@NoArgsConstructor

@Document(collection = "historicos")
public class Historico {
    @Id
    private String idHistorico;
    private LocalDate dataHistorico;
    private String status; // PENDENTE, EM_ANDAMENTO, CONCLUIDO, CANCELADO

    @DBRef
    private Item item;
}
