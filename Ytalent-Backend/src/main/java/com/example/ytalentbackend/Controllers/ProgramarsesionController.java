package com.example.ytalentbackend.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ytalentbackend.Models.Programarsesion;
import com.example.ytalentbackend.Services.ProgramarsesionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/programarsesion")
@RequiredArgsConstructor

public class ProgramarsesionController {

    @Autowired
    private ProgramarsesionService service;

    @GetMapping
    public List<Programarsesion> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programarsesion> getById(@PathVariable Integer id) {
        Optional<Programarsesion> programarsesion = service.findById(id);
        return programarsesion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Programarsesion create(@RequestBody Programarsesion Programarsesion) {
        return service.save(Programarsesion);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Programarsesion> update(@PathVariable Integer id, @Valid @RequestBody Programarsesion programarsesion) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        programarsesion.setId(id);
        Programarsesion updated = service.save(programarsesion);
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
    public ResponseEntity<Programarsesion> updateEstado(@PathVariable Integer id, @RequestBody String estado) {
        Programarsesion updatedProgramarsesion = service.updateEstado(id, estado);
        if (updatedProgramarsesion != null) {
            return ResponseEntity.ok(updatedProgramarsesion);
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
