package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Repository.VerificationCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    private final VerificationCodeRepository repository;

    public VerificationCodeService(VerificationCodeRepository repository) {
        this.repository = repository;
    }

    public void saveVerificationCode(Integer userId, String code) {
        repository.saveVerificationCode(userId, code);
    }

    public boolean validateVerificationCode(Integer userId, String code) {
        // Recupera el código almacenado y compara con el código proporcionado
        String storedCode = repository.getVerificationCode(userId);
        if (storedCode != null && storedCode.equals(code)) {
            // El código es válido, lo eliminamos después de la validación
            repository.removeVerificationCode(userId);
            return true;
        }
        return false;
    }
}
