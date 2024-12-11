package com.example.ytalentbackend.Auth;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

@PostMapping(value = "/register")
public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequest request) {
    try {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    } catch (DataIntegrityViolationException ex) {
        // Manejo de errores de integridad (por ejemplo, correo duplicado)
        String errorMessage = "El correo ya existe. Por favor, usa otro correo.";
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Error de registro")
                .errors(Map.of("correo", errorMessage))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    } catch (EntityNotFoundException ex) {
        // Si algún recurso no se encuentra (Estado o Rol)
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Error: Estado o Rol no encontrado")
                .errors(Map.of("detalle", ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    } catch (Exception ex) {
        // Para cualquier otro error
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Error interno del servidor.")
                .errors(Map.of("detalle", ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

    @PostMapping(value = "logout")
    public ResponseEntity<AuthResponse> logout(@RequestBody LogoutRequest request) {
        authService.logout(request);
        return ResponseEntity.ok(authService.logout(request));
    }
}