package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @Column(name = "id_administrador")
    private String id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
