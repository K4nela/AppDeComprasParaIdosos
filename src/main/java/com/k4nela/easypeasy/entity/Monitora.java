package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "id_idoso")
    @JsonIgnoreProperties("monitora")
    private Idoso idoso;  // referência ao idoso monitorado

    @ManyToOne
    @JoinColumn(name = "id_familiar")
    @JsonIgnoreProperties("monitora")
    private Familiar familiar;  // referência ao familiar que monitora

}
