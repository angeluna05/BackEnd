package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Celulas;
import com.example.ytalentbackend.Services.CelulasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/celulas")
@RequiredArgsConstructor

public class CelulasController {

    @Autowired
    private CelulasService service;

    @GetMapping
    public List<Celulas> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Celulas> getById(@PathVariable Integer id) {
        Optional<Celulas> celulas = service.findById(id);
        return celulas.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Celulas> create(@Validated @RequestBody Celulas celulas) {
        Celulas savedCelulas = service.save(celulas);
        return ResponseEntity.ok(savedCelulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Celulas> update(@PathVariable Integer id, @Validated @RequestBody Celulas celulas) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        celulas.setId(id);
        Celulas updated = service.save(celulas);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok("Eliminado con éxito");
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Celulas> updateEstado(@PathVariable Integer id,@Validated @RequestBody String estado) {
        Celulas updatedCelulas = service.updateEstado(id, estado);
        if (updatedCelulas != null) {
            return ResponseEntity.ok(updatedCelulas);
        } else {
            return ResponseEntity.notFound().build();
        }
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
