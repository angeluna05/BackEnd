package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Instituciones;
import com.example.ytalentbackend.Models.Usuarios;
import com.example.ytalentbackend.Services.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.thymeleaf.util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    @Autowired
    private UsuariosService service;

    // Inyectamos el PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuarios> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getById(@PathVariable Integer id) {
        Optional<Usuarios> usuarios = service.findById(id);
        return usuarios.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuarios> create(@Validated @RequestBody Usuarios usuarios) {
        usuarios.setContrasena(passwordEncoder.encode(usuarios.getContrasena()));
        Usuarios usuariosSaved = service.save(usuarios);
        return ResponseEntity.ok(usuariosSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> update(@PathVariable Integer id, @RequestBody Usuarios usuarios) {
        // Buscar si el usuario existe
        Optional<Usuarios> existingUsuario = service.findById(id);
        
        if (!existingUsuario.isPresent()) {
            return ResponseEntity.notFound().build();  // Si no existe el usuario, devuelve 404
        }
    
        Usuarios usuarioDB = existingUsuario.get();  // Usuario existente en la base de datos
    
        // Actualiza los campos con los nuevos valores del objeto recibido
        usuarioDB.setCorreo(usuarios.getCorreo());
        usuarioDB.setNombre(usuarios.getNombre());
        usuarioDB.setEstadoid(usuarios.getEstadoid());
        usuarioDB.setRolid(usuarios.getRolid());
    
        // Verificar si la contraseña ha cambiado comparando las contraseñas encriptadas
        if (!usuarios.getContrasena().equals(usuarioDB.getContrasena())) {
            System.out.println("Contraseña actualizada");
            usuarioDB.setContrasena(encryptPassword(usuarios.getContrasena()));
        }
    
        // Guardar el usuario actualizado
        Usuarios updated = service.save(usuarioDB);
        return ResponseEntity.ok(updated);
    }
    

public String encryptPassword(String password) {
    // Implementa la lógica para encriptar la contraseña, por ejemplo, con BCrypt
    return passwordEncoder.encode(password); // Usando el PasswordEncoder inyectado
}



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-by-correo")
    public ResponseEntity<Optional<Usuarios>> getUserByCorreo(@RequestParam String correo) {
        Optional<Usuarios> usuario = service.findByCorreo(correo);
        return usuario.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Usuarios> updateEstado(@PathVariable Integer id, @RequestBody Integer estadoId) {
        Usuarios updateUsuarios = service.updateEstado(id, estadoId);
        if (updateUsuarios != null) {
            return ResponseEntity.ok(updateUsuarios);
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
