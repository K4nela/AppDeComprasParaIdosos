package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, String> {
}
