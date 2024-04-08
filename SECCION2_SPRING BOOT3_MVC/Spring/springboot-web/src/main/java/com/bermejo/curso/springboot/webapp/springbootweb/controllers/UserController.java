package com.bermejo.curso.springboot.webapp.springbootweb.controllers;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bermejo.curso.springboot.webapp.springbootweb.models.User;

@Controller
public class UserController {

    @GetMapping("/details")    
    public String details(Model model){
        User user = new User("Ruben", "Carbajal");
        user.setEmail("andres@correo.com");
        model.addAttribute("title", "Hola Mundo SPring Boot");
        model.addAttribute("user", user);
        
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        // List<User> users = Arrays.asList(
        //     new User("Winny","Quispe","winny@gmail.com"),
        //     new User("Liz","Avalos","liz@gmail.com"),
        //     new User("Narda","Quispe"),
        //     new User("Zoely","Quilli","Zoeli@gmail.com")
        // );

        // model.addAttribute("users", users);
        model.addAttribute("title", "Lista en thymeleaf");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> userModel() {
        return  Arrays.asList(
            new User("Winny","Quispe","winny@gmail.com"),
            new User("Liz","Avalos","liz@gmail.com"),
            new User("Narda","Quispe"),
            new User("Zoely","Quilli","Zoeli@gmail.com")
        );
    }
}
