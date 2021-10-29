package com.izo.veterinaria.repository;

import com.izo.veterinaria.model.Peluquero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PeluqueroRepository extends JpaRepository <Peluquero, Long> {
    @Query("SELECT p FROM Peluquero p WHERE p.id = ?1")
    Optional<Peluquero> buscarId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Peluquero o WHERE o.id = ?1")
    void eliminar(Long id);
}