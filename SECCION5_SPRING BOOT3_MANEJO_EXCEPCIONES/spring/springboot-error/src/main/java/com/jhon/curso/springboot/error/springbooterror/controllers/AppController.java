package com.jhon.curso.springboot.error.springbooterror.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhon.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.jhon.curso.springboot.error.springbooterror.models.domain.User;
import com.jhon.curso.springboot.error.springbooterror.services.UserService;

@RestController
@RequestMapping("/app") //es un componente general
public class AppController {

    @Autowired
    private UserService service;
    
    @GetMapping    
    public String index(){
        //int value = 100/0;
        int value = Integer.parseInt("20");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id){
        //User user = service.findById(id); //sin usar Optional        
        // if(user == null){
        //     throw new UserNotFoundException("Error el Usuario No existe");
        // } // sin usar Option
        User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el Usuario No existe"));
        //System.out.println(user.getName());
        return user;
    }

    // @GetMapping("/show/{id}")  //Otra manera para controlar Excepcion pero utilizando ResponseEntity NotFound
    // public ResponseEntity<?> show(@PathVariable(name = "id") Long id){
    //     Optional<User> optionalUser = service.findById(id);
        
    //     if (optionalUser.isEmpty()) {
    //         return ResponseEntity.notFound().build();            
    //     }
        
    //     return ResponseEntity.ok(optionalUser.orElseThrow());
    // }



}
