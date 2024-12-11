package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Retosempresas;
import com.example.ytalentbackend.Services.RetosempresasService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/retosempresas")
@RequiredArgsConstructor

public class RetosempresasController {

    @Autowired
    private RetosempresasService service;

    @GetMapping
    public List<Retosempresas> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retosempresas> getById(@PathVariable Integer id) {
        Optional<Retosempresas> retosempresas = service.findById(id);
        return retosempresas.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Retosempresas create(@RequestBody Retosempresas retosempresas) {
        return service.save(retosempresas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Retosempresas> update(@PathVariable Integer id, @RequestBody Retosempresas retosempresas) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        retosempresas.setId(id);
        Retosempresas updated = service.save(retosempresas);
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
