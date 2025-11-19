package com.k4nela.easypeasy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"idoso", "familiar", "administrador"}) // ajusta conforme a entidade

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String telefone;
    private String tipo;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Idoso idoso;
}
