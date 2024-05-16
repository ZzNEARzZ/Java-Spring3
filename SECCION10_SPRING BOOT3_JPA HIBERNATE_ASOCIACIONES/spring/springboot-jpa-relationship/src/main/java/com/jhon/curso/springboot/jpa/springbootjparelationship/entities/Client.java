package com.jhon.curso.springboot.jpa.springbootjparelationship.entities;

//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "lastname")
    private String lastName;

    //Recordar list no alguanta doble left join; Entonces utilizaremos otras colecciones como "Set" que esta mas optimizado

    //@JoinColumn(name = "client_id")  //El JoinColum hace referencia a un Fk ya creado en la BD siempre y cuando aya una relacion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "tbl_clientes_to_direcciones", 
        joinColumns = @JoinColumn(name= "id_cliente"),
        inverseJoinColumns = @JoinColumn(name="id_direcciones"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client") //  Muy importante es el mappedBy para definir la relacion inversa
    private Set<Invoice> invoices;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente_detalle")
    private ClientDetails clientDetails;

    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastName = lastname;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Client addInvoice(Invoice invoice){ //Metodo con el cual podemos agregar bidireccionalmente; manejamos la relacion inversa en este metodo
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }
    public void removeInvoice(Invoice invoice) { //Metodo para Eliminar Bidireccionalmente
        this.getInvoices().remove(invoice);
        invoice.setClient(null);
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", lastname=" + lastName +
                ", invoices=" + invoices +
                ", addresses=" + addresses +
                ", clientDetails=" + clientDetails +
                "}";
    }

}
