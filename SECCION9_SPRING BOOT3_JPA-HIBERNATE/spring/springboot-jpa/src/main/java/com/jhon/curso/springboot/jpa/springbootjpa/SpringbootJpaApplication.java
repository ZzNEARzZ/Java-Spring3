package com.jhon.curso.springboot.jpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jhon.curso.springboot.jpa.springbootjpa.dto.PersonDto;
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
			//create();
			update();
			//delete2();
			//personalizeQueries2();
			//whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn(){
		System.out.println("===================== Consulta Where In =====================");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries(){
		System.out.println("===================== Consulta por el nombre mas corto y su largo =====================");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name= "+ name + ", length= "+ length);

		});

		System.out.println("===================== Consulta para obtener el ultimo registro de person =====================");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation(){
		
		System.out.println("===================== Consulta con el total de registros de la tabla persona =====================");
		Long count = repository.getTotalPerson();
		System.out.println(count);

		System.out.println("===================== Consulta con el valor minimo del id =====================");
		Long min = repository.getMinId();
		System.out.println(min);

		System.out.println("===================== Consulta con el valor maximo del id =====================");
		Long max = repository.getMaxId();
		System.out.println(max);

		System.out.println("===================== Consulta con el nombre y su Largo =====================");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name= "+ name + ", length= "+ length);
		});
		
		System.out.println("===================== Consulta con el nombre mas corto =====================");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("===================== Consulta con el nombre mas largo =====================");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("===================== Consultas resumen de funciones de agregacion min, max, sum, avg, count =====================");

		Object[] resumeReg =(Object[]) repository.getResumeAggregationFunction();
		System.out.println(
			"min="+ resumeReg[0] +
			", max="+ resumeReg[1]+
			", sum=" + resumeReg[2] +
			", avg=" + resumeReg[3] + 
			", count=" + resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween(){
		System.out.println("===================== Consulta por Rangos Id =====================");
		List<Person> persons = repository.findByIdBetweenOrderByNameAsc(2L, 8L);
		persons.forEach(System.out::println);

		System.out.println("===================== Consulta por Rangos Name =====================");
		persons = repository.findByNameBetweenOrderByNameDescLastNameAsc("J", "Q");
		persons.forEach(System.out::println);

		persons = repository.findAllByOrderByNameAscLastNameDesc();
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizeQueriesConcatUpperAndLowerCase(){
		System.out.println("===================== Consulta nombres y apellidos de personas =====================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("===================== Consulta nombres y apellidos en Mayuscula =====================");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);
		System.out.println("===================== Consulta nombres y apellidos en Minuscula =====================");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("===================== Consulta personalizada persona upper y lower case =====================");
		List<Object[]> regs = repository.findAllPersonDataListCase();
		regs.forEach(reg ->System.out.println("id= "+reg[0]+", nombre= "+ reg[1]+", apellido= "+ reg[2]+", Lenguaje de Programacion= "+reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizeQueriesDistinct(){

		System.out.println("===================== Consultas con nombres de personas =====================");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("===================== Consultas con nombres unicos de personas =====================");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("===================== Consultas con Lenguajes de Programacion Unicos =====================");
		List<String> languages = repository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("===================== Consulta Total de Lenguajes de Programacion Unicos =====================");
		Long totalLanguage = repository.findAllNamesDistinctCount();
		System.out.println("Total de Lenguages de programacion: "+ totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizeQueries2(){
		
		System.out.println("===================== Consulta por Objeto Persona y Lenguaje de Programacion =====================");

		List<Object[]> personsRegs = repository.findAllMixPerson();

		personsRegs.forEach(reg ->{
			System.out.println("programmingLanguage=" + reg[1] + ", person="+ reg[0]);
		});

		System.out.println("===================== Consulta que puebla y devuelve objeto entity de una instancia personalizada =====================");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("===================== Consulta que puebla y devuelve objeto Dto de una instancia personalizada =====================");
		List<PersonDto> person = repository.findAllPersonDto();
		person.forEach(System.out::println);



	}

	@Transactional(readOnly = true)
	public void personalizeQueries(){
		Scanner scanner = new Scanner(System.in);

		System.out.println("===================== Consulta solo el nombre por el id =====================");
		System.out.println("Ingrese el id:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("================== Mostrando solo el nombre ==================");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("================== Mostrando solo el Id ==================");
		Long idDb = repository.getIdById(id);
		System.out.println(idDb);

		System.out.println("================== Mostrando  nombre completo con Concat ==================");
		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("================== Consuilta personalizada por el id ==================");
		Optional<Object> optionalReg =  repository.obtenerPersonDataById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg  = (Object[]) optionalReg.orElseThrow();
			System.out.println("id= "+personReg[0]+", nombre= "+ personReg[1]+", apellido= "+ personReg[2]+", Lenguaje de Programacion= "+personReg[3]);
		}

		System.out.println("================== Consuilta por campos personalizados Lista ==================");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg ->System.out.println("id= "+reg[0]+", nombre= "+ reg[1]+", apellido= "+ reg[2]+", Lenguaje de Programacion= "+reg[3]));
	}

	@Transactional
	public void delete2(){

		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id a Eliminar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(
			person -> repository.delete(person),
			 () -> System.out.println("Lo sentimos no existe la persona con ese Id"));
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}
	
	@Transactional
	public void delete(){

		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id a Eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		//optionalPerson.ifPresent(person -> {
		if (optionalPerson.isPresent()) {
			Person personDB = optionalPerson.orElseThrow();
			System.out.println(personDB);
			System.out.println("Ingrese el legunaje de Programacion");
			String programmingLanguage = scanner.next();			
			personDB.setProgrammingLanguage(programmingLanguage);
			Person personUpdate = repository.save(personDB);
			System.out.println(personUpdate);
		}else{
			System.out.println("No existe la Persona");
		}
		scanner.close();

		//});
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
		System.out.println("Probando"+personNew);
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
