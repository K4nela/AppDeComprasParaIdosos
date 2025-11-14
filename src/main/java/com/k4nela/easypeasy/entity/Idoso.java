package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor

@Document(collection = "idosos")
public class Idoso {
    @Id
    private String idIdoso;

    @DBRef
    private Usuario usuario;
}
