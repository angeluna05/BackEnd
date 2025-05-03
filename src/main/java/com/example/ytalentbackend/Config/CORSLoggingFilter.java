package com.example.ytalentbackend.Config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CORSLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CORSLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro, si es necesario.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("CORS request from: " + request.getRemoteAddr());
        chain.doFilter(request, response); // Pasa la solicitud al siguiente filtro o controlador
    }

    @Override
    public void destroy() {
        // Destrucción del filtro, si es necesario.
    }
}
