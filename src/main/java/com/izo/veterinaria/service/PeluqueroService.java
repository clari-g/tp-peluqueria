package com.izo.veterinaria.service;


import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Turno;
import com.izo.veterinaria.repository.PeluqueroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeluqueroService {
    private final PeluqueroRepository peluqueroRepository;

    @Autowired
    public PeluqueroService(PeluqueroRepository peluqueroRepository) {
        this.peluqueroRepository = peluqueroRepository;
    }

    // Métodos

    /* Buscar todos los peluqueros */
    public List<Peluquero> listar() {
        return peluqueroRepository.findAll();
    }

    /* Buscar peluqueros por id */
    public Peluquero buscarId(Long id) {
        return peluqueroRepository.buscarId(id).get();
    }

    /* Guardar un nuevo peluquero */
    public Peluquero guardar(Peluquero peluquero) {
        if (peluquero != null) {
            return peluqueroRepository.save(peluquero);
        }
        return new Peluquero();
    }

    /* Eliminar un peluquero */
    public String eliminar(Integer id)  {
        String respuesta = "El peluquero de id " + id + " no existe.";
        if(peluqueroRepository.buscarId(id.longValue()) != null) {
            peluqueroRepository.eliminar(id.longValue());
            respuesta = "El peluquero de id " + id + " ha sido eliminado.";
        }
        return respuesta;
    }

    /* Actualizar un peluquero */
    public Peluquero actualizar(Peluquero peluquero) {
        return peluqueroRepository.save(peluquero);
    }
}
