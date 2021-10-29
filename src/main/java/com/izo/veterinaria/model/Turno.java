package com.izo.veterinaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Turno {
    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Long id;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "perro_id", referencedColumnName="id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Perro perro;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "peluquero_id", referencedColumnName="id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Peluquero peluquero;
    private LocalDate date;
    private LocalTime time;

    // Constructores
    public Turno() {
    }

    public Turno(Perro perro, Peluquero peluquero, LocalDate date, LocalTime time) {
        this.perro = perro;
        this.peluquero = peluquero;
        this.date = date;
        this.time = time;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Perro getPerro() {
        return perro;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    public Peluquero getPeluquero() {
        return peluquero;
    }

    public void setPeluquero(Peluquero peluquero) {
        this.peluquero = peluquero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
