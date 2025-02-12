package com.example.ytalentbackend.Config;

import java.util.Arrays;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
        // Cargar permisos y roles
        List<Permisosroles> permisosRoles = permisosRolesService.obtenerTodosLosPermisosRoles();
        Map<String, List<String>> permisosPorRol = permisosRoles.stream()
                .collect(Collectors.groupingBy(
                        pr -> pr.getRolid().getNombre(), // Usa el nombre del rol tal como está en el token
                        Collectors.mapping(pr -> pr.getPermisosid().getNombre(), Collectors.toList())
                ));

        // Imprimir permisosPorRol en consola
        // System.out.println("Permisos por Rol (en SecurityConfig):");
        // permisosPorRol.forEach((rol, permisos) -> {
        //     System.out.println("Rol: " + rol + " -> Permisos: " + permisos);
        // });

        // Configuración de seguridad
        return http
                .csrf(cs -> cs.disable()) // Deshabilitar CSRF si es necesario
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/api/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/usuarios/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/tipodocumentos/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/instituciones/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/roles/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/estado/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/programarsesionprogramarsesion/**").permitAll() // Permitir acceso sin autenticación
                        .requestMatchers("/**").authenticated() // Permitir acceso con autenticación
                //        .requestMatchers(new PermissionBasedRequestMatcher(permisosPorRol)).authenticated() // Permitir acceso basado en permisos

                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://front-end-udo9.onrender.com", "http://localhost:4200")); // Permite solicitudes desde tu frontend Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
