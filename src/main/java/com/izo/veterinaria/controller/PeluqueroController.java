package com.izo.veterinaria.controller;

import com.izo.veterinaria.exceptions.ResourceNotFoundException;
import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.service.PeluqueroService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/peluqueros")
public class PeluqueroController {
    Logger logger = Logger.getLogger("peluquero.log");

    @Autowired
    PeluqueroService peluqueroService;

    @GetMapping
    public ResponseEntity<List<Peluquero>> listar() {
        return ResponseEntity.ok(peluqueroService.listar());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Peluquero> getById(@PathVariable Integer id) {
        Peluquero peluquero = null;
        try {
            peluquero = peluqueroService.buscarId(id.longValue());
            logger.info("Ya busqué el peluquero " + peluquero);
            return ResponseEntity.ok(peluquero);
        } catch(Exception e) {
            logger.info("Ha habido un error: " + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search/{matricula}")
    public ResponseEntity<Peluquero> buscar(@PathVariable Integer matricula) throws ResourceNotFoundException {
        Peluquero peluquero = null;
        try {
            peluquero = peluqueroService.buscar(matricula);
            logger.info("GET matricula peluquero " + peluquero);
        } catch(Exception e) {
            logger.info("Ha habido un error: " + e);
        }
        return ResponseEntity.ok(peluquero);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Peluquero> guardar(@RequestBody Peluquero peluquero) {
        return ResponseEntity.ok(peluqueroService.guardar(peluquero));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;
        try {
            if (peluqueroService.buscarId(id.longValue()) != null) {
                peluqueroService.eliminar(id);
                response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.info("Ha habido un error: " + e);
            return ResponseEntity.ok("No pudo ser eliminado");
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Peluquero> actualizar(@RequestBody Peluquero peluquero) throws ResourceNotFoundException {
        ResponseEntity<Peluquero> response = null;
        try {
            if (peluqueroService.buscarId(peluquero.getId()) != null) {
                response = ResponseEntity.ok(peluqueroService.actualizar(peluquero));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.info("Ha habido un error: " + e);
        }
        return response;
    }
}
