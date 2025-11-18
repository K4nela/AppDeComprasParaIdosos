package com.k4nela.easypeasy.repository;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListaDeDesejosRepository extends JpaRepository<ListaDeDesejos, String> {
    List<ListaDeDesejos> findByIdosoId(String idosoId);

    Optional<ListaDeDesejos> findByIdAndIdosoId(int id, String idosoId);

    List<ListaDeDesejos> findByIdoso(Idoso idoso);

    Optional<ListaDeDesejos> findByIdAndIdoso(int id, Idoso idoso);
}
