package com.khoudidev.inventorymanagementsystem.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        Response errorResponse = Response.builder()
                .status(HttpStatus.UNAUTHORIZED.value()) // Code HTTP 401
                .message(authException.getMessage()) // Message d'erreur
                .build();

        // Définir le type de réponse en JSON
        response.setContentType("application/json");

        // Définir le statut HTTP (401)
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Écrire la réponse JSON dans le corps de la réponse
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
