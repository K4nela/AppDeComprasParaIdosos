package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Historico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends MongoRepository<Historico, String> {
}
