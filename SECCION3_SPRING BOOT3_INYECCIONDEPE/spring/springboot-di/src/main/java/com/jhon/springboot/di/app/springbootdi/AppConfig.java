package com.jhon.springboot.di.app.springbootdi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;

import com.jhon.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.jhon.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
})
public class AppConfig {

   
    @Value("classpath:json/product.json")  // con esto permite leer datos desde un archivo json de forma declarativa
    private Resource resource;
    @Bean("productJson")    
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }

}
