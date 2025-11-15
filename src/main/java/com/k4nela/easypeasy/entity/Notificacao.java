package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

@Document(collection = "notificacoes")
public class Notificacao {
    @Id
    private String id;
    private String titulo;
    private String mensagem;
    private boolean lida;
    private LocalDateTime data;
}

