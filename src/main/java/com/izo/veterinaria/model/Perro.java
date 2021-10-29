package com.izo.veterinaria.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
public class Perro {


    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Long id;
    private String nombre;
    private String raza;
    private String dueno;

    // Constructores
    public Perro() {
    }

    public Perro(String nombre, String raza, String dueno) {
        this.nombre = nombre;
        this.raza = raza;
        this.dueno = dueno;
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

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public Perro setear(Perro perro) {
        this.nombre = perro.getNombre();
        this.raza = perro.getRaza();
        this.dueno = perro.getDueno();
        return perro;
    }

}
