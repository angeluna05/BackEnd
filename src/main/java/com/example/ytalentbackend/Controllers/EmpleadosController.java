package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Empleados;
import com.example.ytalentbackend.Services.EmpleadosService;
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
@RequestMapping("/empleados")
@RequiredArgsConstructor

public class EmpleadosController {

    @Autowired
    private EmpleadosService service;

    @GetMapping
    public List<Empleados> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleados> getById(@PathVariable Integer id) {
        Optional<Empleados> empleado = service.findById(id);
        return empleado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleados create(@RequestBody Empleados empleado) {
        return service.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleados> update(@PathVariable Integer id, @RequestBody Empleados empleado) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        empleado.setId(id);
        Empleados updated = service.save(empleado);
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
    public ResponseEntity<Empleados> changeEstado(@PathVariable Integer id, @RequestBody Integer nuevoEstadoId) {
        Empleados updatedEmpleado = service.changeEstado(id, nuevoEstadoId);
        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
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
