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

@Document(collection = "familiares")
public class Familiar {
    @Id
    private String idFamiliar;
    @DBRef
    private com.k4nela.easypeasy.entity.Usuario usuario;
}
