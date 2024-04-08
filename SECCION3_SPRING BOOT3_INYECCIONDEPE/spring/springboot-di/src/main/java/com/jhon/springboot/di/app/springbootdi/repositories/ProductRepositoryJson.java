package com.jhon.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhon.springboot.di.app.springbootdi.models.Product;

public class ProductRepositoryJson implements ProductRepository{

    private List<Product> list;
    
    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");  // Este clase permite leer datos json desde un archivo
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) {
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        
        ObjectMapper objectMapper = new ObjectMapper(); // convertir un archo json file imput stream a un objeto de java

        try {
            list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        
        return list;
    }

    @Override
    public Product findById(Long id) {
        
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

    

}
