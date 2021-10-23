package com.izo.veterinaria.controller;

import com.izo.veterinaria.exceptions.ResourceNotFoundException;
import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Perro;
import com.izo.veterinaria.model.Turno;
import com.izo.veterinaria.service.PeluqueroService;
import com.izo.veterinaria.service.PerroService;
import com.izo.veterinaria.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    Logger logger = Logger.getLogger("turno.log");

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PerroService perroService;
    @Autowired
    private PeluqueroService peluqueroService;

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Turno> getById(@PathVariable Integer id) {
        Turno turno = null;
        try {
            turno = turnoService.buscarId(id.longValue());
            logger.info("Ya busqué el turno " + turno);
            return ResponseEntity.ok(turno);
        } catch(Exception e) {
            logger.info("Ha habido un error: " + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response = null;
        if (turno != null) {
            Perro perro = perroService.buscarId(turno.getPerro().getId());
            turno.setPerro(perro);
            Peluquero peluquero = peluqueroService.buscar(turno.getPeluquero().getMatricula());
            turno.setPeluquero(peluquero);
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;
        try {
            if (turnoService.buscarId(id.longValue()) != null) {
                turnoService.eliminar(id);
                response = ResponseEntity.ok("Turno eliminado");
            } else {
                response = ResponseEntity.ok("Turno no encontrado");
            }
        } catch (Exception e) {
            logger.info("Ha habido un error: " + e);
            return ResponseEntity.ok("No pudo ser eliminado");
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) throws ResourceNotFoundException {
        ResponseEntity<Turno> response = null;
        try {
            if (turnoService.buscar(turno.getId().intValue()) != null) {
                response = ResponseEntity.ok(turnoService.actualizar(turno));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.info("Ha habido un error: " + e);
        }
        return response;
    }
}