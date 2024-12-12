package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Services.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor

public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getById(@PathVariable Integer id) {
        Optional<Estado> estado = service.findById(id);
        return estado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estado create(@RequestBody Estado estado) {
        return service.save(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable Integer id, @RequestBody Estado estado) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estado.setId(id);
        Estado updated = service.save(estado);
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
