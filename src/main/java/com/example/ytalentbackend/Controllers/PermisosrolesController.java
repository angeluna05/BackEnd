package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Permisos;
import com.example.ytalentbackend.Services.PermisosrolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/permisosroles")
@RequiredArgsConstructor
public class PermisosrolesController {

    private final PermisosrolesService service; // Inyección de dependencias usando constructor

    @GetMapping
    public List<Permisos> getAll() {
        return service.findAll();
    }

    @GetMapping("/rol/{rolId}/permisos")
    public ResponseEntity<List<Permisos>> listarPermisosPorRolId(@PathVariable Integer rolId) {
        List<Permisos> permisos = service.listarPermisosPorRolId(rolId);
        return ResponseEntity.ok(permisos);
    }

// Endpoint para asignar un permiso a un rol usando el ID del rol
@PostMapping("/asignar")
public ResponseEntity<Map<String, String>> asignarPermisoARol(@RequestParam Integer rolId, @RequestParam String nombrePermiso) {
    service.asignarPermisoARol(nombrePermiso, rolId);
    
    Map<String, String> response = new HashMap<>();
    response.put("message", "Permiso asignado con éxito");
    
    return ResponseEntity.ok(response);
}

// Endpoint para eliminar un permiso de un rol usando el ID del rol
@DeleteMapping("/eliminar")
public ResponseEntity<Map<String, String>> eliminarPermisoDeRol(@RequestParam Integer rolId, @RequestParam String nombrePermiso) {
    service.eliminarPermisoDeRol(nombrePermiso, rolId);
    
    Map<String, String> response = new HashMap<>();
    response.put("message", "Permiso eliminado con éxito");
    
    return ResponseEntity.ok(response);
}



    @GetMapping("/{id}")
    public ResponseEntity<Permisos> getById(@PathVariable Integer id) {
        Optional<Permisos> permisos = service.findById(id);
        return permisos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<Permisos> create(@RequestBody List<Permisos> permisosList) {
        return service.saveAll(permisosList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permisos> update(@PathVariable Integer id, @RequestBody Permisos permisos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        permisos.setId(id);
        Permisos updated = service.save(permisos);
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
