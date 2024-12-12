package com.example.ytalentbackend.Controllers;


import com.example.ytalentbackend.Models.InscripcionCelulas;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Services.InscripcionCelulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inscripcion-celulas")
public class InscripcionCelulasController {

    @Autowired
    private InscripcionCelulasService service;

    @GetMapping
    public List<InscripcionCelulas> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionCelulas> getById(@PathVariable Integer id) {
        Optional<InscripcionCelulas> inscripcionCelulas = service.findById(id);
        return inscripcionCelulas.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public InscripcionCelulas create(@RequestBody InscripcionCelulas inscripcionCelulas) {
        return service.save(inscripcionCelulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionCelulas> update(@PathVariable Integer id, @RequestBody InscripcionCelulas inscripcionCelulas) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inscripcionCelulas.setId(id);
        InscripcionCelulas updated = service.save(inscripcionCelulas);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
