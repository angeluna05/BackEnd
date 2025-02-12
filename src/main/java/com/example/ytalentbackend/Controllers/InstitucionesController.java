package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Celulas;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Models.Instituciones;
import com.example.ytalentbackend.Services.InstitucionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/instituciones")
@RequiredArgsConstructor

public class InstitucionesController {

    @Autowired
    private InstitucionesService service;

    @GetMapping
    public List<Instituciones> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instituciones> getById(@PathVariable Integer id) {
        Optional<Instituciones> instituciones = service.findById(id);
        return instituciones.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instituciones> create(@Validated @RequestBody Instituciones institucionesList) {
        Instituciones institucionessaved = service.save(institucionesList);
        return ResponseEntity.ok(institucionessaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituciones> update(@PathVariable Integer id, @RequestBody Instituciones instituciones) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        instituciones.setId(id);
        Instituciones updated = service.save(instituciones);
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
    public ResponseEntity<Instituciones> updateEstado(@PathVariable Integer id, @RequestBody Integer estadoId) {
        Instituciones updatedInstituciones = service.updateEstado(id, estadoId);
        if (updatedInstituciones != null) {
            return ResponseEntity.ok(updatedInstituciones);
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
