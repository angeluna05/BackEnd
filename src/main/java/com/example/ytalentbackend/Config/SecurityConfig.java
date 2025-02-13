package com.example.ytalentbackend.Config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ytalentbackend.Jwt.JwtAuthenticationFilter;
import com.example.ytalentbackend.Services.PermisosrolesService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final PermisosrolesService permisosRolesService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(cs -> cs.disable()) 
                .cors(cors -> cors.disable()) // Deshabilita CORS en Spring Security para usar CorsFilter
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/tipodocumentos", "/estado").permitAll() // 🌟 Solo estas rutas son públicas
                        .anyRequest().authenticated() // 🔒 El resto necesita autenticación
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
