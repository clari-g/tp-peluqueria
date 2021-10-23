package com.izo.veterinaria.controller;

import com.izo.veterinaria.exceptions.ResourceNotFoundException;
import com.izo.veterinaria.model.Peluquero;
import com.izo.veterinaria.model.Perro;
import com.izo.veterinaria.service.PeluqueroService;
import com.izo.veterinaria.service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/perros")
public class PerroController {
    Logger logger = Logger.getLogger("perro.log");

    @Autowired
    PerroService perroService;

    @GetMapping
    public ResponseEntity<List<Perro>> listar() {
        return ResponseEntity.ok(perroService.listar());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Perro> buscar(@PathVariable Integer id) throws ResourceNotFoundException {
        Perro perro = null;
        try {
            perro = perroService.buscarId(id.longValue());
            logger.info("Ya busqué el perro " + perro);
            return ResponseEntity.ok(perro);
        } catch(Exception e) {
            logger.info("Ha habido un error: " + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Perro> guardar(@RequestBody Perro perro) {
        return ResponseEntity.ok(perroService.guardar(perro));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;
        try {
            if (perroService.buscarId(id.longValue()) != null) {
                perroService.eliminar(id);
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
    public ResponseEntity<Perro> actualizar(@RequestBody Perro perro) throws ResourceNotFoundException {
        ResponseEntity<Perro> response = null;
        try {
            if (perroService.buscarId(perro.getId()) != null) {
                response = ResponseEntity.ok(perroService.actualizar(perro));
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
        }
        return response;
    }
}
