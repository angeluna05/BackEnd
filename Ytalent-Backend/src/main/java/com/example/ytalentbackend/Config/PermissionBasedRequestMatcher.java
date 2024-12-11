package com.example.ytalentbackend.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PermissionBasedRequestMatcher implements RequestMatcher {

    private final Map<String, List<String>> permisosPorRol;

    public PermissionBasedRequestMatcher(Map<String, List<String>> permisosPorRol) {
        this.permisosPorRol = permisosPorRol;
        // Imprimir permisosPorRol en la inicialización
        System.out.println("Permisos por Rol (en PermissionBasedRequestMatcher):");
        permisosPorRol.forEach((rol, permisos) -> {
            System.out.println("Rol: " + rol + " -> Permisos: " + permisos);
        });
    }
    @Override
    public boolean matches(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // Obtener el rol del usuario autenticado y eliminar el prefijo "ROLE_"
        String rol = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(authority -> authority.replace("ROLE_", "")) // Elimina el prefijo "ROLE_"
                .orElse("");

        // Obtener permisos del rol y asegurarse de que cada permiso tenga el prefijo "/"
        List<String> permisos = permisosPorRol.get(rol);
        if (permisos == null) {
            permisos = List.of(); // Si permisos es null, inicializa una lista vacía
        }
        permisos = permisos.stream()
                .map(permission -> permission.startsWith("/") ? permission : "/" + permission)
                .collect(Collectors.toList());

        // Imprimir permisos y otros detalles para depuración
        String requestURI = request.getRequestURI();
        System.out.println("Evaluando Request URI: " + requestURI);
        System.out.println("User Role: " + rol);
        System.out.println("Permissions for Role: " + permisos);

        // Verificar si la ruta solicitada coincide con los permisos del rol
        boolean matches = permisos.stream()
                .anyMatch(permission -> {
                    // Imprimir la ruta de permiso que se está evaluando
                    String permissionWithWildcard = permission.endsWith("/**") ? permission : permission + "/**";
                    System.out.println("Comparando con Permiso: " + permissionWithWildcard);
                    return requestURI.startsWith(permissionWithWildcard) || requestURI.equals(permission);
                });

        System.out.println("Coincide: " + matches);
        return matches;
    }

}