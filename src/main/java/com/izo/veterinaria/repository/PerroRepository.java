package com.izo.veterinaria.repository;

import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PerroRepository extends JpaRepository <Perro, Long> {
    @Query("SELECT p FROM Perro p WHERE p.id = ?1")
    Optional<Perro> buscarId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Perro o WHERE o.id = ?1")
    void eliminar(Long id);
}