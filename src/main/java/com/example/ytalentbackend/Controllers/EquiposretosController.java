package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Equiposretos;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Services.EquiposretosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equiposretos")
@RequiredArgsConstructor

public class EquiposretosController {

    @Autowired
    private EquiposretosService service;

    @GetMapping
    public List<Equiposretos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equiposretos> getById(@PathVariable Integer id) {
        Optional<Equiposretos> equiposretos = service.findById(id);
        return equiposretos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/equipos/{id}")
    public List<Equiposretos> findByEquipoid(@PathVariable Integer id) {
        Equipos equipos = new Equipos();
        equipos.setId(id);
        return service.findByEquipoid(equipos);
    }
    @PostMapping
    public Equiposretos create(@RequestBody Equiposretos equiposretos) {
        return service.save(equiposretos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equiposretos> update(@PathVariable Integer id, @RequestBody Equiposretos equiposretos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        equiposretos.setId(id);
        Equiposretos updated = service.save(equiposretos);
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
