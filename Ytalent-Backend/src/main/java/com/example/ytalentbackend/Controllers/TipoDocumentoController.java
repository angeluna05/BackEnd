package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Tipodocumento;
import com.example.ytalentbackend.Services.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tipodocumentos")
@RequiredArgsConstructor

public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public List<Tipodocumento> getAllTipoDocumentos() {
        return tipoDocumentoService.getAllTipoDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipodocumento> getTipoDocumentoById(@PathVariable Integer id) {
        Optional<Tipodocumento> tipoDocumento = tipoDocumentoService.getTipoDocumentoById(id);
        return tipoDocumento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tipodocumento> createTipoDocumento(@RequestBody Tipodocumento tipoDocumento) {
        Tipodocumento createdTipoDocumento = tipoDocumentoService.saveTipoDocumento(tipoDocumento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipodocumento> updateTipoDocumento(@PathVariable Integer id, @RequestBody Tipodocumento Tipodocumento) {
        Optional<Tipodocumento> existingTipoDocumento = tipoDocumentoService.getTipoDocumentoById(id);
        if (existingTipoDocumento.isPresent()) {
            Tipodocumento.setId(id);
            Tipodocumento updatedTipoDocumento = tipoDocumentoService.saveTipoDocumento(Tipodocumento);
            return ResponseEntity.ok(updatedTipoDocumento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoDocumento(@PathVariable Integer id) {
        Optional<Tipodocumento> existingTipoDocumento = tipoDocumentoService.getTipoDocumentoById(id);
        if (existingTipoDocumento.isPresent()) {
            tipoDocumentoService.deleteTipoDocumento(id);
            return ResponseEntity.noContent().build();
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
