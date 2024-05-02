package com.jhon.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jhon.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.jhon.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

    @Query("select p from Person p where p.id not in ?1")  // Podemos Usar el (in) para indicarle cuales ids; como tambien podemor utilizar el (not in) para que sea diferente podemos decir como una negacion.
    public List<Person> getPersonsByIds(List<Long> ids);

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select max(p.id) from Person p")
    Long getMinId();

    @Query("select min(p.id) from Person p")
    Long getMaxId();

    List<Person> findAllByOrderByNameAscLastNameDesc();

    @Query("select p from Person p order by p.name, p.lastName desc")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastNameAsc(String name1, String name2);
    
    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastName desc")  // no se esta Incluyendo la 'P'
    List<Person> findAllBetweenName(String c1, String c2);


    @Query("select p.id, upper(p.name), lower(p.lastName), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();
    
    @Query("select upper(p.name || ' ' || p.lastName) from Person p") // Otra forma de Concatenar
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(concat(p.name, ' ', p.lastName)) from Person p")
    List<String> findAllFullNameConcatLower();
    
    //@Query("select concat(p.name, ' ', p.lastName) from Person p")
    @Query("select p.name || ' ' || p.lastName from Person p") // Otra forma de Concatenar
    List<String> findAllFullNameConcat();

    @Query("select count(distinct p.programmingLanguage) from Person p")
    Long findAllNamesDistinctCount();
    
    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct p.name from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct p.programmingLanguage from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select new com.jhon.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.name, ' ', p.lastName) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);


    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name); //No necesita Query por que se basa en su nomenclatura

    Optional<Person> findByNameContaining(String name); // sin anotacion Query

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);
   

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguaje);
    

   

}
