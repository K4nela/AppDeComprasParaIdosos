package com.k4nela.easypeasy.controller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface logRepository extends MongoRepository<log, String> {
}
