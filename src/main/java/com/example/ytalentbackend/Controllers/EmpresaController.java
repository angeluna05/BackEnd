package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Empresa;
import com.example.ytalentbackend.Services.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor

public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @GetMapping
    public List<Empresa> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getById(@PathVariable Integer id) {
        Optional<Empresa> empresa = service.findById(id);
        return empresa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Empresa create(@RequestBody Empresa empresa) {
        return service.save(empresa);
    }


    @PutMapping("/{id}")
    public Empresa update(@PathVariable Integer id, @RequestBody Empresa empresa) {
        empresa.setId(id);
        return service.save(empresa);
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
    public ResponseEntity<Empresa> updateEstado(@PathVariable Integer id, @RequestBody Integer estadoId) {
        Empresa updatedEmpresa = service.updateEstado(id, estadoId);
        if (updatedEmpresa != null) {
            return ResponseEntity.ok(updatedEmpresa);
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
