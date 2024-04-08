package com.jhon.curso.springboot.error.springbooterror.services;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jhon.curso.springboot.error.springbooterror.models.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("datos")
    private List<User> users;

    // public UserServiceImpl() {
    //     this.users = new ArrayList<>();
    //     users.add(new User(1L, "Pepe", "Gonzales"));
    //     users.add(new User(2L, "Mitzi", "Condori"));
    //     users.add(new User(3L, "Winny", "Molina"));
    //     users.add(new User(4L, "Josefa", "Ramirez"));
    //     users.add(new User(5L, "Liz", "Gutierrez"));
    // }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {

       /*Utilizando lo normal Optional Normal */
        // User user = null;
        // for(User u : users){
        //     if(u.getId().equals(id)){
        //         user = u;
        //         break;
        //     }            
        // }
        // //  return user; // Cuando no se utiliza un Optional
        // return Optional.ofNullable(user);

        /* Aqui utilizamos un poco de programacion Funcional */

        Optional<User> user = users.stream().filter(usr -> usr.getId().equals(id)).findFirst();
        return user;
    }



}
