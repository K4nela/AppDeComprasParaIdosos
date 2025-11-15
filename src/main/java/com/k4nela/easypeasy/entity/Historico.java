package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @Column(name = "id_historico")
    private int id;

    @Column(name = "data_historico", insertable = false, updatable = false)
    private LocalDate dataHistorico;

    private String status; // PENDENTE, EM_ANDAMENTO, CONCLUIDO, CANCELADO

    @OneToOne
    @JoinColumn(name = "id_item")
    private Item item;
}
