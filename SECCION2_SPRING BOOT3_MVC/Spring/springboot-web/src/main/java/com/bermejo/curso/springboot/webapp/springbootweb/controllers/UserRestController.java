package com.bermejo.curso.springboot.webapp.springbootweb.controllers;


// import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bermejo.curso.springboot.webapp.springbootweb.models.User;
import com.bermejo.curso.springboot.webapp.springbootweb.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")    
    public UserDto details(){
        
        User user = new User("Ruben", "Carbajal");
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("SPring Boot con DTO");
       
        // Map<String, Object> body = new HashMap<>();
        // body.put("title", "SPring Boot");
        // body.put("user", user);
       
        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user = new User("Winny", "Carbajal");
        User user2 = new User("liz", "Avalos");
        User user3 = new User("zoeli", "Quilli");

        List<User> users = Arrays.asList(user, user2, user3);
        // List<User> users = new ArrayList<>();
        // users.add(user);
        // users.add(user2);
        // users.add(user3);

        return users;        
    }

    @GetMapping("/details-map")    
    public Map<String, Object> detailsMap(){

        User user = new User("Renato", "Carbajal");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "SPring Boot con Map");
        body.put("user", user);
       
        return body;
    }
}
