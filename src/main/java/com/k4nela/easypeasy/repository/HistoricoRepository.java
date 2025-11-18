package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, String> {
    Optional<Historico> findTopByItemIdOrderByDataHistoricoDesc(int itemId);
}
