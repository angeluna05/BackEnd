package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Services.EquiposService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor

public class EquiposController {

    @Autowired
    private EquiposService service;

    @GetMapping
    public List<Equipos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipos> getById(@PathVariable Integer id) {
        Optional<Equipos> equipos = service.findById(id);
        return equipos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipos create(@RequestBody Equipos equipos) {
        return service.save(equipos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipos> update(@PathVariable Integer id, @RequestBody Equipos equipos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        equipos.setId(id);
        Equipos updated = service.save(equipos);
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
