package com.example.ytalentbackend.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

import com.example.ytalentbackend.Models.Inscripcionsesiones;
import com.example.ytalentbackend.Models.Programarsesion;
import com.example.ytalentbackend.Services.InscripcionsesionesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscripcionsesiones")
@RequiredArgsConstructor
public class InscripcionsesionesController {

    @Autowired
    private final InscripcionsesionesService service;

    @GetMapping
    public List<Inscripcionsesiones> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcionsesiones> getById(@PathVariable Integer id) {
        Optional<Inscripcionsesiones> inscripcionsesione = service.findById(id);
        return inscripcionsesione.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<Inscripcionsesiones> create(@Validated @RequestBody List<Inscripcionsesiones> inscripcionsesiones) {
        return service.saveAll(inscripcionsesiones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcionsesiones> update(@PathVariable Integer id, @Validated @RequestBody Inscripcionsesiones inscripcionsesione) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inscripcionsesione.setId(id);
        Inscripcionsesiones updated = service.save(inscripcionsesione);
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

    @PutMapping("/estado/{id}")
    public ResponseEntity<Inscripcionsesiones> updateEstado(@PathVariable Integer id, @RequestBody String nuevoEstado) {
        return service.updateEstado(id, nuevoEstado)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
@GetMapping("/by-sesionesid/{sesionesid}")
public ResponseEntity<List<Inscripcionsesiones>> getBySesionesid(@PathVariable("sesionesid") Integer sesionesId) {
    Programarsesion programarSesionid = new Programarsesion(sesionesId); // Asumiendo que tienes un constructor en Programarsesion que toma un ID
    List<Inscripcionsesiones> inscripciones = service.findByProgramarSesionid(programarSesionid);
    if (inscripciones.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(inscripciones);
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
