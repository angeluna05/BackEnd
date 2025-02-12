package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Auth.PasswordRecoveryRequest;
import com.example.ytalentbackend.Services.usuariosService;
import com.example.ytalentbackend.Models.usuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/password-recovery")
@RequiredArgsConstructor
public class PasswordRecoveryController {

    private final usuariosService usuariosService;

    @PostMapping("/send-code")
    public ResponseEntity<ApiResponse> sendRecoveryCode(@RequestBody PasswordRecoveryRequest request) {
        String correo = request.getCorreo();

        if (correo == null || correo.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("Parametro 'correo' es requerido"));
        } else {
            usuariosService.sendPasswordRecoveryEmail(correo);
            return ResponseEntity.ok(new ApiResponse("Código de recuperación enviado a " + correo));
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<ApiResponse> verifyCode(@RequestBody PasswordRecoveryRequest request) {
        String code = request.getCode();
        Integer userId = request.getUserId();

        boolean isValid = usuariosService.validateVerificationCode(userId, code);
        if (isValid) {
            return ResponseEntity.ok(new ApiResponse("Código de verificación válido. "+userId+" "+code));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Código inválido o expirado."));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody PasswordRecoveryRequest request) {
        String newPassword = request.getNewPassword();
        Integer userId = request.getUserId();

        Optional<usuarios> optionalUsuario = usuariosService.findById(userId);

        if (optionalUsuario.isPresent()) {
            usuarios usuario = optionalUsuario.get();
            usuario.setContrasena(encryptPassword(newPassword)); // Asegúrate de encriptar la contraseña
            usuariosService.save(usuario);
            return ResponseEntity.ok(new ApiResponse("Contraseña restablecida exitosamente. " +newPassword));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Usuario no encontrado."));
        }
    }

    public String encryptPassword(String password) {
        // Implementa la lógica para encriptar la contraseña, por ejemplo, con BCrypt
        return BCrypt.hashpw(password, BCrypt.gensalt()); // Ejemplo usando BCrypt
    }

    // Clase interna para la estructura de respuesta
    public static class ApiResponse {
        private final String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
