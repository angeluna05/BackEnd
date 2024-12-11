package com.example.ytalentbackend.Auth;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class BlacklistService {

    // En un escenario real, podrías usar una base de datos para persistir esta información
    private final Set<String> blacklistedTokens = new HashSet<>();

    public void addTokenToBlacklist(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
