package com.k4nela.easypeasy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

@Document(collection = "log")
public class Log {

    @Id
    private String id;
    private String mensagem;
    private String nivel;
    private String origem;
    private String detalhe;
    private LocalDateTime data;
}
