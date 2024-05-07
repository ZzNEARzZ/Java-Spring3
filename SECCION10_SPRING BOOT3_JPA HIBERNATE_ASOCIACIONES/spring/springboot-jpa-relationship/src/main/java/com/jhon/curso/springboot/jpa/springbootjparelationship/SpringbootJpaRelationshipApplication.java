package com.jhon.curso.springboot.jpa.springbootjparelationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		oneToMany();
		
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Franck", "Escobar");

		Address address1 = new Address("Santa Rosa",327);
		Address address2 = new Address("Llavini", 745);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void manyToOne(){

		Client client = new Client("jhon", "Bermejo");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de Oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);

	}

	@Transactional
	public void manyToOneFindByIdClient(){

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {

			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de Deportivas", 1000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
			
		}
		


	}



}
