package br.com.confitec.teste.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * Exception Handler simples, para lidar com algumas exceptions que o calculo de parcelas pode sofrer.
 *
 * @author Matheus Martins Baptista
 */

@ControllerAdvice
public class ApoliceExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
