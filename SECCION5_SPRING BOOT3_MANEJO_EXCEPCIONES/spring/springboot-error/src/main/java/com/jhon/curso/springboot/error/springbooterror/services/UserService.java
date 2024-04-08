package com.jhon.curso.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import com.jhon.curso.springboot.error.springbooterror.models.domain.User;

public interface UserService {

    List<User> findAll();
    // User findById(Long id); //Usamos User como tambien unn Optional
    Optional<User> findById(Long id);


}
