package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, String> {
}
