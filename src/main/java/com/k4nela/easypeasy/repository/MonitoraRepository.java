package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Monitora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonitoraRepository extends JpaRepository<Monitora, String> {
    long countByIdosoId(String idosoId);

    long countByFamiliarId(String familiarId);

    Optional<Monitora> findByIdosoId(String idosoId);

//    Optional<Monitora> findByFamiliarId(String familiarId);

    List<Monitora> findByFamiliarId(String familiarId);
}
