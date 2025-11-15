package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Monitora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdosoRepository extends JpaRepository<Idoso, String> {
    List<Monitora> findByFamiliarId(String familiarId);
}
