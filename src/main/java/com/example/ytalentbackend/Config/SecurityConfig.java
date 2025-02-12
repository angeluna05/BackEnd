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
import com.example.ytalentbackend.Models.Permisosroles;
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
        // Cargar permisos y roles desde la BD
        List<Permisosroles> permisosRoles = permisosRolesService.obtenerTodosLosPermisosRoles();
        Map<String, List<String>> permisosPorRol = permisosRoles.stream()
                .collect(Collectors.groupingBy(
                        pr -> pr.getRolid().getNombre(), // Usa el nombre del rol tal como está en el token
                        Collectors.mapping(pr -> pr.getPermisosid().getNombre(), Collectors.toList())
                ));

        return http
                .csrf(cs -> cs.disable()) // Deshabilitar CSRF si es necesario
                .cors().and() // 🔥 Ahora CORS lo maneja `SimpleCorsFilter`
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/api/**", "/usuarios/**", "/tipodocumentos/**", 
                                         "/instituciones/**", "/roles/**", "/estado/**", 
                                         "/programarsesion/**") // 🔥 Corregí el doble `programarsesion`
                        .permitAll()
                        .requestMatchers("/**").authenticated() // Rutas protegidas
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
