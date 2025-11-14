package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor

@Document(collection = "logs")
public class Log {

    @Id
    private String id;
    private String usuario;
    private String acao;
    private String dataHora;
}
