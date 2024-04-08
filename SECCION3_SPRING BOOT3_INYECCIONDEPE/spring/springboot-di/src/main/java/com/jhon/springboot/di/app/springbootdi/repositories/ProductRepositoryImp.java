package com.jhon.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;

import com.jhon.springboot.di.app.springbootdi.models.Product;

@Primary
//@SessionScope //Esto maneja por sesiones activas y cuando hace  logaout se destruye
//@RequestScope  //Es por cliente que se comuinica se elimina o destruye la peticion (No se inmuta la misma data solo una sesion)
@Repository("productList") //a este componente se le puede poner un nombre especifico para poder inyectarlo en otro
public class ProductRepositoryImp implements ProductRepository{

    private List<Product> data;

    public ProductRepositoryImp() {
        this.data = Arrays.asList(
            new Product(1L, "Memoria corsair", 32L),
            new Product(2L, "Cpu Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mini 60", 180L),
            new Product(4L, "Motheboard Gigabyte", 490L)

        );
    }

    @Override
    public List<Product> findAll() {
        return this.data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }


    

}
