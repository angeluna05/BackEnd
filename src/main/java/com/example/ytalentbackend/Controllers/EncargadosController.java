package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Encargados;
import com.example.ytalentbackend.Services.EncargadosService;
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
@RequestMapping("/encargados")
@RequiredArgsConstructor

public class EncargadosController {

    @Autowired
    private EncargadosService service;

    @GetMapping
    public List<Encargados> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encargados> getById(@PathVariable Integer id) {
        Optional<Encargados> encargado = service.findById(id);
        return encargado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Encargados create(@RequestBody Encargados encargado) {
        return service.save(encargado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encargados> update(@PathVariable Integer id, @RequestBody Encargados encargado) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        encargado.setId(id);
        Encargados updated = service.save(encargado);
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
    @PutMapping("/{id}/estado")
    public ResponseEntity<Encargados> updateEstado(@PathVariable Integer id, @RequestBody Integer estadoId) {
        Encargados updatedEncargado = service.updateEstado(id, estadoId);
        if (updatedEncargado != null) {
            return ResponseEntity.ok(updatedEncargado);
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
