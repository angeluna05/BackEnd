package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Jovenesinstituciones;
import com.example.ytalentbackend.Services.JovenesinstitucionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jovenesinstituciones")
@RequiredArgsConstructor

public class JovenesinstitucionesController {

    @Autowired
    private JovenesinstitucionesService service;

    @GetMapping
    public List<Jovenesinstituciones> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jovenesinstituciones> getById(@PathVariable Integer id) {
        Optional<Jovenesinstituciones> jovenesinstituciones = service.findById(id);
        return jovenesinstituciones.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jovenesinstituciones create(@RequestBody Jovenesinstituciones jovenesinstituciones) {
        return service.save(jovenesinstituciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jovenesinstituciones> update(@PathVariable Integer id, @RequestBody Jovenesinstituciones jovenesinstituciones) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jovenesinstituciones.setId(id);
        Jovenesinstituciones updated = service.save(jovenesinstituciones);
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
