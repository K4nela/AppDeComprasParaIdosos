package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

@Entity
@Table(name = "monitora")
public class Monitora {

    @Id
    @Column(name = "id_monitora")
    private int id;

    @ManyToOne
    private Idoso idoso;  // referência ao idoso monitorado

    @ManyToOne
    private Familiar familiar;  // referência ao familiar que monitora

}
