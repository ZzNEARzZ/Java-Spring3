package com.jhon.bermejo.springboot.di.factura.springbootdifactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.jhon.bermejo.springboot.di.factura.springbootdifactura.models.Item;
import com.jhon.bermejo.springboot.di.factura.springbootdifactura.models.Product;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
})
public class AppConfig {

    @Bean("default")
    //@Primary
    List<Item> itemsInvoce(){
        Product p1 = new Product("Camara Sony",800);
        Product p2 = new Product("Bicilceta Bianchi26", 1200);
        Product p3 = new Product("MacBook Pro", 12500);
        List<Item> items= Arrays.asList(new Item(p1,1),new Item(p2,2),new Item(p3,1));
        return items;
    }
    @Bean("nextq")
    List<Item> itemsInvoceOffice(){
        Product p1 = new Product("Monitor Asus 24",700);
        Product p2 = new Product("Notebook Razer", 2400);
        Product p3 = new Product("Impresora HP", 800);
        Product p4 = new Product("Escritorio Oficina", 900);
        List<Item> items= Arrays.asList(new Item(p1,4),new Item(p2,6),new Item(p3,1),new Item(p4,4));
        return items;
    }
}


