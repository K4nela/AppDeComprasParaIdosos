package com.k4nela.easypeasy.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

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
    private String dataNasc;
}
