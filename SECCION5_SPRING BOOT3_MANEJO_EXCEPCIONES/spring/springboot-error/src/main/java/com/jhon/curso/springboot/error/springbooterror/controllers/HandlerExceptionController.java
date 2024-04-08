package com.jhon.curso.springboot.error.springbooterror.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jhon.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.jhon.curso.springboot.error.springbooterror.models.Error;
 
@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<Error> divisionByZero(Exception ex) { //Aqui se utiliza el ResponseEntity por eso en el return se devuelve un Response
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        //return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler({NullPointerException.class, 
        HttpMessageNotWritableException.class,
        UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    public Map<String,Object> userNotFoundException(Exception ex){  //aqui se esta utilizando un Map por eso ahi un componente Response status y solo se devuelve el map

        Map<String,Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "El usuario o Rol no existe");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    public Map<String,Object> numberFormatException(Exception ex){  //aqui se esta utilizando un Map por eso ahi un componente Response status y solo se devuelve el map

        Map<String,Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Numero invalido o incorrecto, no tiene formato de Digito");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException ex){
        
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api no encontrado");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

}
