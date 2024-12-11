package com.example.ytalentbackend.Services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class PasswordResetService {

    private Map<String, LocalDateTime> tokenStore = new HashMap<>(); // Almacenamiento en memoria para simplificación

    public void createPasswordResetToken(String email) {
        String token = UUID.randomUUID().toString(); // Genera un token único
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // Define una expiración para el token
        tokenStore.put(token, expiryDate);

        // Aquí deberías enviar el token al usuario por correo electrónico o SMS
        sendTokenToUser(email, token);
    }

    private void sendTokenToUser(String email, String token) {
        // Implementa el envío del token por correo o SMS
        // Ejemplo: emailService.sendResetPasswordEmail(email, token);
    }
}
