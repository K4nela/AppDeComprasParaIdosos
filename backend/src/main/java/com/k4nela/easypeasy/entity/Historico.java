package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @Column(name = "id_historico")
    private int id;

    @CreationTimestamp
    @Column(name = "data_historico", insertable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHistorico;

    private String status; // PENDENTE, EM_ANDAMENTO, CONCLUIDO, CANCELADO

    @OneToOne
    @JoinColumn(name = "id_item")
    private Item item;
}
