package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Retos;
import com.example.ytalentbackend.Services.RetosService;
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
@RequestMapping("/retos")
@RequiredArgsConstructor

public class RetosController {

    @Autowired
    private RetosService service;

    @GetMapping
    public List<Retos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retos> getById(@PathVariable Integer id) {
        Optional<Retos> retos = service.findById(id);
        return retos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Retos> create(@Valid @RequestBody Retos retos) {
        Retos savedRetos = service.save(retos);
        return ResponseEntity.ok(savedRetos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Retos> update(@PathVariable Integer id, @Valid @RequestBody Retos retos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        retos.setId(id);
        Retos updated = service.save(retos);
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
    public ResponseEntity<Retos> updateEstado(@PathVariable Integer id,@Validated @RequestBody String estado) {
        Retos updatedRetos = service.updateEstado(id, estado);
        if (updatedRetos != null) {
            return ResponseEntity.ok(updatedRetos);
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
