package com.example.ytalentbackend.Auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ytalentbackend.Jwt.JwtService;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Roles;
import com.example.ytalentbackend.Models.Usuarios;
import com.example.ytalentbackend.Repository.EstadoRepository;
import com.example.ytalentbackend.Repository.RolesRepository;
import com.example.ytalentbackend.Services.JovenesService;
import com.example.ytalentbackend.User.UsuariosRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuariosRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final EstadoRepository estadoRepository;
    private final RolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;
    private final BlacklistService blacklistService; // Inyecta el servicio de lista negra
    @Autowired
    private JovenesService service;

    public AuthResponse login(LoginRequest request) {
        // Autenticación del usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));

        // Obtener detalles del usuario
        Usuarios user = userRepository.findByCorreo(request.getCorreo()).orElseThrow();

        // Obtener el rol por ID
        Roles rol = rolesRepository.findById(user.getRolid().getId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<String> permisos = userRepository.findPermisosByUsuarioId(user.getRolid().getId());
        permisos.add("JOVEN");
        permisos.add("ADMIN");
        permisos.add("EMPLEADO");

        // Generar el token JWT
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .rolName(rol.getNombre()) // Asume que el rol tiene un método getName()
                .message("Inicio de sesión exitoso")
                .permisos(permisos)
                .user(user) // Añadido
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        // Verifica si el usuario ya existe
        Optional<Usuarios> existingUser = userRepository.findByCorreo(request.getCorreo());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }
    
        // Obtener estado y rol
        Estado estado = estadoRepository.findById(request.getEstadoid())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado."));
        Roles rol = rolesRepository.findById(request.getRolid())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado."));
    
        // Crear el usuario
        Usuarios user = Usuarios.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .estadoid(estado)
                .rolid(rol)
                .build();
        userRepository.save(user);
    
        // Crear y guardar el joven
        Jovenes joven = Jovenes.builder()
                .documento(request.getDocumento())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .fechaNacimiento(request.getFechaNacimiento())
                .numeroContacto(request.getNumeroContacto())
                .correo(request.getCorreo())
                .tipo_institucion(request.getTipo_institucion())
                .institucion(request.getInstitucion())
                .habilidades(request.getHabilidades())
                .caracteristicas(request.getCaracteristicas())
                .descripcion(request.getDescripcion())
                .tipoDocumentoid(request.getTipoDocumentoid())
                .estadoid(estado)
                .build();
        service.save(joven);
    
        // Generar token y permisos
        String token = jwtService.getToken(user);
        List<String> permisos = userRepository.findPermisosByUsuarioId(user.getRolid().getId());
        permisos.add(rol.getNombre());
    
        return AuthResponse.builder()
                .token(token)
                .rolName(rol.getNombre())
                .message("Registro exitoso.")
                .user(user)
                .permisos(permisos)
                .build();
    }
    

    public AuthResponse logout(LogoutRequest request) {
        blacklistService.addTokenToBlacklist(request.getToken());
        return AuthResponse.builder()
                .message("Sesión destruída")
                .build();
    }

}
