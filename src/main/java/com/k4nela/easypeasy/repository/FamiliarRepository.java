package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Familiar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends MongoRepository<Familiar, String> {
}
