package com.jhon.curso.springboot.jpa.springbootjpa;

import java.util.List;
//import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jhon.curso.springboot.jpa.springbootjpa.entities.Person;
import com.jhon.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;     //EL PersonRepòsitory extiende de crudRepository y devuelve un Optional no un objeto por eso se castea

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

			//list();
			//findOne();
			create();
	}

	@Transactional
	public void create(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Nombre:");
		String name  = scanner.next();
		System.out.println("Ingrese el Apellido:");
		String lastname = scanner.next();
		System.out.println("Ingrese el el Lenguaje de Programacion:");
		String programmingLanguage = scanner.next();
		scanner.close();
		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);
		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne(){

		/*Person person = repository.findById(7L).orElseThrow(); // Utilizamos orElseThro para capturar y a su vez verificar si contiene datos en conclusion si no tiene captura una exception
		System.out.println(person);*/
		/*Person person = null;
		Optional<Person> optionalPerson = repository.findById(6L);
		String namePerson = optionalPerson.get().getName();
		//if(!optionalPerson.isEmpty()){
		if(optionalPerson.isPresent()){
			person = optionalPerson.get(); // Con .get se captura el Objeto
			System.out.println(person);
		}else{
			System.out.println("No ahi persona con Id Ingresado");
		}*/
		repository.findByNameContaining("se").ifPresent(System.out::println);  //Esta es otra forma de capturar e imorimir un objeto

	}

	@Transactional(readOnly = true)
	public void list(){

		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java","Maria");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java","Andres");

		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsValues = repository.obtenerPersonData("Maria");
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
			//System.out.println(person);
		});	

	}

}
