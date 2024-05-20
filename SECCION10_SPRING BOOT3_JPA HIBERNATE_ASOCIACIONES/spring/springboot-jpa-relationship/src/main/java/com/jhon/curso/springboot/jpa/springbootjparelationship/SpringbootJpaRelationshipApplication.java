package com.jhon.curso.springboot.jpa.springbootjparelationship;

//import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Course;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.jhon.curso.springboot.jpa.springbootjparelationship.entities.Student;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.ClientDetailsRepository;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.CourseRepository;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import com.jhon.curso.springboot.jpa.springbootjparelationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		manyToManyRemoveByDireccionalFind();	
	
	}

	@Transactional
	public void manyToManyRemoveByDireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		// Course course1 = new Course("Curso de java master", "Andre");
		// Course course2 = new Course("Curso de Spring Boot", "Andre");

		Course course1 = courseRepository.findOneWithStudents(1l).get();
		Course course2 = courseRepository.findOneWithStudents(2l).get();

		// student1.setCourses(Set.of(course1,course2));
		// student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if (courseOptionalDb.isPresent()) {

				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);
				// studentDb.getCourses().remove(courseDb);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

			
		}

	}

	@Transactional
	public void manyToManyByDireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		// Course course1 = new Course("Curso de java master", "Andre");
		// Course course2 = new Course("Curso de Spring Boot", "Andre");

		Course course1 = courseRepository.findOneWithStudents(1l).get();
		Course course2 = courseRepository.findOneWithStudents(2l).get();

		// student1.setCourses(Set.of(course1,course2));
		// student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyByDireccionalRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Winny", "Zamalloa");

		Course course1 = new Course("Curso de java master", "Andre");
		Course course2 = new Course("Curso de Spring Boot", "Andre");

		// student1.setCourses(Set.of(course1,course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if (courseOptionalDb.isPresent()) {

				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);
				// studentDb.getCourses().remove(courseDb);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

			
		}

	}

	@Transactional
	public void manyToManyByDireccional() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Winny", "Zamalloa");

		Course course1 = new Course("Curso de java master", "Andre");
		Course course2 = new Course("Curso de Spring Boot", "Andre");

		// student1.setCourses(Set.of(course1,course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Winny", "Zamalloa");

		Course course1 = new Course("Curso de java master", "Andre");
		Course course2 = new Course("Curso de Spring Boot", "Andre");

		student1.setCourses(Set.of(course1,course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {

				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

			
		}

	}

	@Transactional
	public void manyToManyRemoveFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		// Course course1 = new Course("Curso de java master", "Andre");
		// Course course2 = new Course("Curso de Spring Boot", "Andre");

		Course course1 = courseRepository.findById(1l).get();
		Course course2 = courseRepository.findById(2l).get();

		student1.setCourses(Set.of(course1,course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {

				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);
				
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

			
		}

	}

	@Transactional
	public void manyToManyFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		// Course course1 = new Course("Curso de java master", "Andre");
		// Course course2 = new Course("Curso de Spring Boot", "Andre");

		Course course1 = courseRepository.findById(1l).get();
		Course course2 = courseRepository.findById(2l).get();

		student1.setCourses(Set.of(course1,course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Winny", "Zamalloa");

		Course course1 = new Course("Curso de java master", "Andre");
		Course course2 = new Course("Curso de Spring Boot", "Andre");

		student1.setCourses(Set.of(course1,course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> clientOptional = clientRepository.findOne(1L);
		clientOptional.ifPresent(client -> {

			ClientDetails clientDetails = new ClientDetails(true,5000);
	
			client.setClientDetails(clientDetails);
			//clientDetails.setClient(client); //Cuando se obtimiza codigo se pone e la clase setClienDetails
	
			clientRepository.save(client);
			
			System.out.println(client);
		});


	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Erba", "Pura");

		ClientDetails clientDetails = new ClientDetails(true,5000);

		client.setClientDetails(clientDetails);
		//clientDetails.setClient(client); //Cuando se obtimiza codigo se pone e la clase setClienDetails

		clientRepository.save(client);
		
		System.out.println(client);

	}

	@Transactional
	public void oneToOneFindById() {
		
		ClientDetails clientDetails = new ClientDetails(true,5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptinal = clientRepository.findOne(2L);
		clientOptinal.ifPresent(client ->{
			
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
	
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOne() {
		
		ClientDetails clientDetails = new ClientDetails(true,5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Client client = new Client("Roy", "Escobar");
				
	
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de Oficina",8000L);
				
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			clientRepository.save(client);
			System.out.println(client);
		

		Optional<Client> optionalClientDb = clientRepository.findOne(3L);

		optionalClientDb.ifPresent(clientDb -> {
			Invoice invoice3 = new Invoice("Compras de la casa", 5000L); //Recordemos que aqui solo se le asigna la descripcion y el total m,as no el Id puesto que eso se genera al guardar en la Db
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			//Optional<Invoice> invoiceOptional = invoiceRepository.findById(1L); //Recordemos aqui Buscamos por Id
			invoiceOptional.ifPresent(invoice ->{
				clientDb.removeInvoice(invoice);
				/*client.getInvoices().remove(invoice); //El remove funciona por el Id, descripcion y total por que se implemento el hash que sirve para el set
				invoice.setClient(null);*/
				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
			
		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {		
	
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de Oficina",8000L);
				
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(1L);

		optionalClientDb.ifPresent(client -> {
			Invoice invoice3 = new Invoice("Compras de la casa", 5000L); //Recordemos que aqui solo se le asigna la descripcion y el total m,as no el Id puesto que eso se genera al guardar en la Db
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			//Optional<Invoice> invoiceOptional = invoiceRepository.findById(1L); //Recordemos aqui Buscamos por Id
			invoiceOptional.ifPresent(invoice ->{
				client.removeInvoice(invoice);
				/*client.getInvoices().remove(invoice); //El remove funciona por el Id, descripcion y total por que se implemento el hash que sirve para el set
				invoice.setClient(null);*/
				clientRepository.save(client);
				System.out.println(client);
			});
			
		});

	}
	
	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {		
	
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de Oficina",8000L);
				
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {

		Client client = new Client("Roy", "Escobar");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de Oficina",8000L);

		/*client.setInvoices(Arrays.asList(invoice1, invoice2)); //Esta es una forma de agregar a la BD si es bidireccional

		invoice1.setClient(client);
		invoice2.setClient(client);*/
		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);

	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Santa Rosa", 327);
			Address address2 = new Address("Circunvalacion", 354);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			//client.setAddresses(Arrays.asList(address1, address2)); //se utilizaba cuando era List
			clientRepository.save(client);			
			System.out.println(client);	

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address1);   //Recordemos que si ponemos address1 o address2 eston tienen Id nulos ya que estan en otro contexto y no se eliminaran en cambio si ponemos defrente los indice como es una lista se concidera desde el cero; ahi si se elimina.
				clientRepository.save(c);
				System.out.println(c);
			});
					
		});

	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Franck", "Escobar");

		Address address1 = new Address("Santa Peeda",327);
		Address address2 = new Address("Llavini", 745);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		//client.setAddresses(Arrays.asList(address1, address2));
		clientRepository.save(client);
		System.out.println(client);	

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
		

	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Santa Rosa", 327);
			Address address2 = new Address("Circunvalacion", 354);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);
			//client.setAddresses(Arrays.asList(address1, address2)); //Cuando era List

			clientRepository.save(client);

			System.out.println(client);			
		});

	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Franck", "Escobar");

		Address address1 = new Address("Santa Peeda",327);
		Address address2 = new Address("Llavini", 745);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		
		//client.setAddresses(Arrays.asList(address1, address2));

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
