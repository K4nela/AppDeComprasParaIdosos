package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"idoso", "familiar", "administrador", "listasDeDesejos"}) // ajusta conforme a entidade


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
