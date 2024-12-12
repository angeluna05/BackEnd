package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Logros;
import com.example.ytalentbackend.Services.LogrosService;
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
@RequestMapping("/logros")
@RequiredArgsConstructor

public class LogrosController {

    @Autowired
    private LogrosService service;

    @GetMapping
    public List<Logros> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logros> getById(@PathVariable Integer id) {
        Optional<Logros> logros = service.findById(id);
        return logros.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/joven/{id}")
    public List<Logros> getLogrosByJovenId(@PathVariable Integer id) {
        Jovenes jovenes = new Jovenes();
        jovenes.setId(id);
        return service.findByJovenId(jovenes);
    }
    @PostMapping
    public Logros create(@RequestBody Logros logrosList) {
        return service.save(logrosList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Logros> update(@PathVariable Integer id, @RequestBody Logros logros) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        logros.setId(id);
        Logros updated = service.save(logros);
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
