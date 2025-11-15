package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "idoso")
public class Idoso {

    @Id
    @Column(name = "id_idoso")
    private String id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
