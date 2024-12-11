package com.example.ytalentbackend.Repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class VerificationCodeRepository {
    // Almacena los códigos de verificación con el ID del usuario como clave
    private final ConcurrentMap<Integer, String> codeStore = new ConcurrentHashMap<>();

    public void saveVerificationCode(Integer userId, String code) {
        // Almacena el código en el mapa
        codeStore.put(userId, code);
    }

    public String getVerificationCode(Integer userId) {
        // Recupera el código almacenado para el usuario
        return codeStore.get(userId);
    }

    public void removeVerificationCode(Integer userId) {
        // Elimina el código almacenado para el usuario
        codeStore.remove(userId);
    }
}
