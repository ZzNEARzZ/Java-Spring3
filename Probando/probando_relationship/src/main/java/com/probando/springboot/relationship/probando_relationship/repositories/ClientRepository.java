package com.probando.springboot.relationship.probando_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.probando.springboot.relationship.probando_relationship.entities.Client;





public interface ClientRepository extends CrudRepository<Client, Long>{

    

    @Query("select p from Client p where p.id=?1")
    Optional<Client> findOne(Long id);

}


