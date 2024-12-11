package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Permisos;
import com.example.ytalentbackend.Services.PermisosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/permisos")
@RequiredArgsConstructor

public class PermisosController {

    @Autowired
    private PermisosService service;

    @GetMapping
    public List<Permisos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permisos> getById(@PathVariable Integer id) {
        Optional<Permisos> permisos = service.findById(id);
        return permisos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<Permisos> create(@RequestBody List<Permisos> permisosList) {
        return service.saveAll(permisosList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permisos> update(@PathVariable Integer id, @RequestBody Permisos permisos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        permisos.setId(id);
        Permisos updated = service.save(permisos);
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
