package br.edu.iftm.jpw_ferragens.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FormularioException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("\n\n----------------- ControllerAdvice -------------------------");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // @ResponseStatus(HttpStatus.CHECKPOINT)
    // @ExceptionHandler(Exception.class)
    // public String erroGeral(Exception ex) {
    // System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
    // return ex.getMessage();
    // }

    // @ResponseStatus(HttpStatus.CHECKPOINT)
    // @ExceptionHandler(UsernameNotFoundException.class)
    // public String erroAutenticacao(Exception ex) {
    // System.out.println("************************** " + ex.getMessage());
    // return ex.getMessage();
    // }
}
