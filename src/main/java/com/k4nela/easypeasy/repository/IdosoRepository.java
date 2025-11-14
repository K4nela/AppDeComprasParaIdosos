package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.model.Idoso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdosoRepository extends MongoRepository<Idoso, String> {
}
