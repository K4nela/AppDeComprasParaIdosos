package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"idoso", "familiar", "administrador", "listasDeDesejos"}) // ajusta conforme a entidade


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
