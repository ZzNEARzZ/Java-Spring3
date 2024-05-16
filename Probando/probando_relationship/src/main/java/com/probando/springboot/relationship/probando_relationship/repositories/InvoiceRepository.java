package com.probando.springboot.relationship.probando_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.probando.springboot.relationship.probando_relationship.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
