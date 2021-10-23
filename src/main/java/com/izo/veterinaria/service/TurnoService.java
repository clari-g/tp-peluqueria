package com.izo.veterinaria.service;


import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Turno;
import com.izo.veterinaria.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    // Métodos

    /* Buscar todos los Turnos */
    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    /* Buscar Turno por id */
    public Turno buscarId(Long id) {
        return turnoRepository.buscarId(id).get();
    }

    public Turno buscar(Integer id) {
        return turnoRepository.buscar(id).get();
    }

    /* Guardar un nuevo Turno */
    public Turno guardar(Turno turno) {
        if (turno != null) {
            return turnoRepository.save(turno);
        }
        return new Turno();
    }

    /* Eliminar un Turno */
    public String eliminar(Integer id)  {
        String respuesta = "El turno de id " + id + " no existe.";
        if(turnoRepository.buscarId(id.longValue()) != null) {
            turnoRepository.eliminar(id.longValue());
            respuesta = "El turno de id " + id + " ha sido eliminado.";
        }
        return respuesta;
    }

    /* Actualizar un Turno */
    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }
}
