package com.jhon.curso.springboot.error.springbooterror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhon.curso.springboot.error.springbooterror.models.domain.User;

@Configuration
public class AppConfig {

    @Bean("datos")
    List<User> users(){

        List<User> users = new ArrayList<>();
        users.add(new User(1L,"Jhon","Bermejo"));
        users.add(new User(2L, "Pepe", "Gonzales"));
        users.add(new User(3L, "Mitzi", "Condori"));
        users.add(new User(4L, "Winny", "Molina"));
        users.add(new User(5L, "Josefa", "Ramirez"));
        users.add(new User(6L, "Liz", "Gutierrez"));
        return users;

    }

}
