package com.example.ytalentbackend.Config;

import com.example.ytalentbackend.Models.Permisosroles;
import com.example.ytalentbackend.Services.PermisosrolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final PermisosrolesService permisosrolesService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String userRole = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority())
                    .orElse("");

            List<Permisosroles> permisosRoles = permisosrolesService.obtenerTodosLosPermisosRoles();
            boolean hasAccess = permisosRoles.stream()
                    .anyMatch(pr -> pr.getPermisosid().getNombre().equals(requestURI) && pr.getRolid().getNombre().equals(userRole));

            if (!hasAccess) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
