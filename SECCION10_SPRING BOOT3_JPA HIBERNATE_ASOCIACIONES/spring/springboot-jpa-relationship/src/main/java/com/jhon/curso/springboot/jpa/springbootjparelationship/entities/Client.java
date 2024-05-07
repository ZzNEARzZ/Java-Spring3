package com.jhon.curso.springboot.jpa.springbootjparelationship.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "lastname")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //El cascade que cualquier accion en la entidad principal ya sea (agregar, eliminar, actualizar) se proague a las entidades secundarias siempre y cuando se ponga ALL.
    private List<Address> addresses;

    public Client() {
        addresses = new ArrayList<>();
    }

    
    public Client(String name, String lastName) {
        this(); //con esto llamamos al constructor Vacio para asi se inicialice
        this.name = name;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", lastName=" + lastName + ", addresses="+ addresses +"}";
    }    

}
