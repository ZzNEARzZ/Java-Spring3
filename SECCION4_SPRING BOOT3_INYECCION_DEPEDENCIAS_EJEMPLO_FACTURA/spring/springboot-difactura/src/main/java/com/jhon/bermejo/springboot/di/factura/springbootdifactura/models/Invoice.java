package com.jhon.bermejo.springboot.di.factura.springbootdifactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"}) //Esto se ponen cuando el controlador llama a las clases y como estas estan con el componente RequestScope o SessionScope estas devuelven un Json que lo envia enun proxy y esto genera conflicto
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

    public Invoice() {
        System.out.println("Creando componente de Factura");
        System.out.println(client);
        System.out.println(description);

    }

    @PostConstruct // Se inicializa el componente Singleton; a su vez ya se inicializa los atributos esto se diferencia de el constructor vacio ya que este no inicializa los atributos
    public void init(){
        System.out.println("Creando  el componente de factura utilizando PostConstruc lol");
        client.setName(client.getName().concat(" Pepe"));
        description = description.concat(" del Cliente ").concat(client.getName().concat(" ").concat(client.getLastname()));

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Probando Destruyendo el componente o bean invoice");
    }


    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal(){
        // int total=0;
        // for(Item item: items){
        //     total += item.getImporte();
        // }
        // return total;
        int total = items.stream()
        .map(item -> item.getImporte())
        .reduce(0,(sum, importe) -> sum + importe);
        return total;
    }

}
