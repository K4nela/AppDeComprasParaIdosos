package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor

@Document(collection = "administradores")
public class Administrador {
    @Id
    private String idAdministrador;

    @DBRef
    private com.k4nela.easypeasy.entity.Usuario usuario;
}
