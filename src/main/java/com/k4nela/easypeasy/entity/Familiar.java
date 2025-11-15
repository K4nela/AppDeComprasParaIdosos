package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "familiar")
public class Familiar {

    @Id
    @Column(name = "id_familiar")
    private String id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
