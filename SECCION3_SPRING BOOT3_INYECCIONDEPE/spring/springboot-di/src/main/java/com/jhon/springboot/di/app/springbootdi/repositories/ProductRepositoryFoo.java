package com.jhon.springboot.di.app.springbootdi.repositories;


import java.util.Collections;
import java.util.List;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.jhon.springboot.di.app.springbootdi.models.Product;

//@Primary  //Como se ultiliza la interface ProductRepository que tambien lo utiliza la clase ProductRepository con este prymare le digo que esta clase va hacer el por defecto.
@Repository
public class ProductRepositoryFoo implements ProductRepository{

    @Override
    public List<Product> findAll() {
        
        return Collections.singletonList(new Product(1L, "Monitor Asus", 600L));
    }

    @Override
    public Product findById(Long id) {
        
        return new Product(id, "Monitor Asus 27", 600L);
    }

    

}
