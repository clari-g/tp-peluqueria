package com.izo.veterinaria.model;

import net.minidev.json.JSONObject;

import javax.persistence.*;

@Entity
@Table
public class Peluquero {
    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    // Constructores
    public Peluquero() {
    }

    public Peluquero(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Peluquero setear(Peluquero peluquero) {
        this.nombre = peluquero.getNombre();
        this.apellido = peluquero.getApellido();
        this.matricula = peluquero.getMatricula();
        return peluquero;
    }

}