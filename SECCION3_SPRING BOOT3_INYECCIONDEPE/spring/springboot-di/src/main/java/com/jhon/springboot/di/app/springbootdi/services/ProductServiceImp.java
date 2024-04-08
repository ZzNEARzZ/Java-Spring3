package com.jhon.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.jhon.springboot.di.app.springbootdi.models.Product;
import com.jhon.springboot.di.app.springbootdi.repositories.ProductRepository;


@Service
public class ProductServiceImp implements ProductService{

    // @Value("${config.price.tax}") // UNa forma para pasar properties
    // private Double impuesto;

    @Autowired
    private Environment environment;

    //@Autowired //hace como una instgancia de otra objeto
    //@Qualifier("productList") Cuando es por atributo aqui va el quealifier
    private ProductRepository repository;

        
    // @Autowired cuando se utiliza en un constructor no es necesario el autowired
    //@Qualifier hace referencial a cual objeto vamos a inyectar ojo con esto y si se quita el Qualifier se inyecta el primero o el por defecto
    public ProductServiceImp(@Qualifier("productJson") ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        
        return repository.findAll().stream().map(p ->{
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
            // p.setPrice(priceTax.longValue());  //Esto hace que sea mutable ya que modifica a la misma data.
            // return p;
            // Product newPro = new Product(p.getId(), p.getName(), priceTax.longValue()); //una forma de crear inmutabilidad pero ahi otra forma mas recomendada que se muestra en la sighuiente linea           
            Product newProd = new Product();
            newProd = (Product)p.clone();
            newProd.setPrice(priceTax.longValue());            
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }   

}
