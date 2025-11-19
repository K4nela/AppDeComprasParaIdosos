package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"idoso", "familiar", "administrador", "listasDeDesejos"}) // ajusta conforme a entidade

@Entity
@Table(name = "idoso")
public class Idoso {

    @Id
    @Column(name = "id_idoso")
    private String id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "idoso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ListaDeDesejos> desejos = new ArrayList<>();

    @OneToMany(mappedBy = "idoso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Monitora> monitoramentos = new ArrayList<>();
}
