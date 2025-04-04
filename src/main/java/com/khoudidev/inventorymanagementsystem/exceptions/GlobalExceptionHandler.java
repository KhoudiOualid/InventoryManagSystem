package com.khoudidev.inventorymanagementsystem.exceptions;


import com.khoudidev.inventorymanagementsystem.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    // 🟥 Gestion des erreurs générales (Exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> HandleAllExceptions(Exception ex) {
        Response response = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()) // Code HTTP 500
                .message(ex.getMessage()) // Message d'erreur
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🟧 Gestion des erreurs NotFoundException (exemple : ID non trouvé)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> HandleNotFoundExceptions(NotFoundException ex) {
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value()) // Code HTTP 404
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 🟨 Gestion des erreurs lors de la saisie de données incomplètes
    @ExceptionHandler(NameValueRequiredException.class)
    public ResponseEntity<Response> HandleNameValueRequiredException(NameValueRequiredException ex) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value()) // Code HTTP 400
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 🟦 Gestion des erreurs lors de l'authentification
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> HandleInvalidCredentialsException(InvalidCredentialsException ex) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value()) // Code HTTP 400
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




}
