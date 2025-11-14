package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.model.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String> {
}
