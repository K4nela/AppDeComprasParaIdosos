package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.ListaDeDesejos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeDesejosRepository extends JpaRepository<ListaDeDesejos, String> {
}
