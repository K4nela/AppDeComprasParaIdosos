package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.ListaDeDesejos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeDesejosRepository extends MongoRepository<ListaDeDesejos, String> {
}
