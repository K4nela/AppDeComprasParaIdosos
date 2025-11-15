package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends JpaRepository<Familiar, String> {
}
