package com.example.ytalentbackend.Auth;

import com.example.ytalentbackend.Models.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    
    String token;
    String rolName;
    String message;
    List<String> permisos;
    private Usuarios user;
    private Map<String, String> errors; // Campo para errores de validación
}
