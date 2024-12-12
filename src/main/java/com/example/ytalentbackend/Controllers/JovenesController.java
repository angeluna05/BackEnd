package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Services.JovenesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/jovenes")
@RequiredArgsConstructor
public class JovenesController {

    @Autowired
    private JovenesService service;

    @GetMapping
    public List<Jovenes> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jovenes> getById(@PathVariable Integer id) {
        Optional<Jovenes> jovenes = service.findById(id);
        return jovenes.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jovenes create(@RequestBody Jovenes jovenes) {
        return service.save(jovenes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Jovenes> update(@PathVariable Integer id, @Validated @RequestBody Jovenes jovenes) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jovenes.setId(id);
        Jovenes updated = service.save(jovenes);
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
    public ResponseEntity<Jovenes> updateEstado(@PathVariable Integer id,@Validated @RequestBody Integer estadoId) {
        Jovenes updatedJovenes = service.updateEstado(id, estadoId);
        if (updatedJovenes != null) {
            return ResponseEntity.ok(updatedJovenes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
