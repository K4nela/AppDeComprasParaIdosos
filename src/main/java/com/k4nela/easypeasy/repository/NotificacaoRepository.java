package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Notificacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificacaoRepository extends MongoRepository<Notificacao, String> {
}
