package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.model.Familiar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends MongoRepository<Familiar, String> {
}
