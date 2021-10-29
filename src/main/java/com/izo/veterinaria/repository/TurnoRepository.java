package com.izo.veterinaria.repository;

import com.izo.veterinaria.model.Perro;
import com.izo.veterinaria.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository <Turno, Long> {
    @Query("SELECT o FROM Turno o WHERE o.id = ?1")
    Optional<Turno> buscar(Integer id);

    @Query("SELECT p FROM Turno p WHERE p.id = ?1")
    Optional<Turno> buscarId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Turno o WHERE o.id = ?1")
    void eliminar(Long id);
}