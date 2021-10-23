package com.izo.veterinaria.service;


import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Perro;
import com.izo.veterinaria.repository.PerroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerroService {
    private final PerroRepository perroRepository;

    @Autowired
    public PerroService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    // Métodos

    /* Buscar todos los Perros */
    public List<Perro> listar() {
        return perroRepository.findAll();
    }

    /* Buscar Perro por id */
    public Perro buscarId(Long id) {
        return perroRepository.buscarId(id).get();
    }

    public Perro buscar(String nombre) {
        return perroRepository.buscar(nombre).get();
    }

    /* Guardar un nuevo Perro */
    public Perro guardar(Perro perro) {
        if (perro != null) {
            return perroRepository.save(perro);
        }
        return new Perro();
    }

    /* Eliminar un Perro */
    public String eliminar(Integer id)  {
        String respuesta = "El perro de id " + id + " no existe.";
        if(perroRepository.buscarId(id.longValue()) != null) {
            perroRepository.eliminar(id.longValue());
            respuesta = "El perro de id " + id + " ha sido eliminado.";
        }
        return respuesta;
    }

    /* Actualizar un Perro */
    public Perro actualizar(Perro perro) {
        return perroRepository.save(perro);
    }
}
