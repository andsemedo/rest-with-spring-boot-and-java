package com.andsemedodev.restwithspringbootandjava.repositories;

import com.andsemedodev.restwithspringbootandjava.model.Person;
import com.andsemedodev.restwithspringbootandjava.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person = new Person(
                "Anderson",
                "Semedo",
                "Bela Vista - Praia - CV",
                "male",
                "andsemedo023@gmail.com"
        );
    }

    @DisplayName("JUnit test for Given Person Object when Saved the Return Saved Person")
    @Test
    void testGivenPersonObject_whenSaved_thenReturnSavedPerson() {

        // When / Act
        Person savedPerson = personRepository.save(person);

        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
        assertEquals(person.getFirstName(), savedPerson.getFirstName());

    }

    @DisplayName("JUnit test for Given Person Object when FindAll then return Person List")
    @Test
    void testGivenPersonObject_whenFindAll_thenReturnPersonList() {
        // Given / Arrange
        Person person1 = new Person(
                "Isaias",
                "Mendes",
                "Bela Vista - Praia - CV",
                "male",
                "semedoisaias02@gmail.com"
        );

        personRepository.save(person);
        personRepository.save(person1);

        // When / Act
        List<Person> personList = personRepository.findAll();

        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }

    @DisplayName("JUnit test for Given Person Object when FindById then return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Optional<Person> personFound = personRepository.findById(savedPerson.getId());

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound.get());

    }

    @DisplayName("JUnit test for Given Person Object when FindByEmail then return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Optional<Person> personFound = personRepository.findByEmail("andsemedo023@gmail.com");

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound.get());

    }

    @DisplayName("JUnit test for Given Person Object when FindByEmail then return Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPerson() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        savedPerson.setFirstName("Isaias");
        savedPerson.setEmail("semedoisaias02@gmail.com");
        Person updatedPerson = personRepository.save(savedPerson);

        // Then / Assert
        assertNotNull(updatedPerson);
        assertEquals("Isaias", updatedPerson.getFirstName());
        assertEquals("semedoisaias02@gmail.com", updatedPerson.getEmail());

    }

    @DisplayName("JUnit test for Given Person Object when Delete Person then remove person")
    @Test
    void testGivenPersonObject_whenDeletePerson_thenRemovePerson() {
        // Given / Arrange

        personRepository.save(person);

        // When / Act
        personRepository.deleteById(person.getId());

        Optional<Person> personOptional = personRepository.findById(person.getId());

        // Then / Assert
        assertTrue(personOptional.isEmpty());

    }

    @DisplayName("JUnit test for Given Person Object when Delete Person then remove person")
    @Test
    void testGivenPersonObject_whenFindJPQL_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Person personFound = personRepository.findJPQL("Anderson", "Semedo");

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound);

    }

    @DisplayName("JUnit test for Given Person Object when FindByJPQLNamedParameters then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByJPQLNamedParameters_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Person personFound = personRepository.findByJPQLNamedParameters("Anderson", "Semedo");

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound);

    }

    @DisplayName("JUnit test for Given Person Object when FindByNativeSQL then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByNativeSQL_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Person personFound = personRepository.findByNativeSQL("Anderson", "Semedo");

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound);

    }

    @DisplayName("JUnit test for Given Person Object when FindByNativeSQLWithNamedParams then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByNativeSQLWithNamedParams_thenReturnPersonObject() {
        // Given / Arrange

        Person savedPerson = personRepository.save(person);

        // When / Act
        Person personFound = personRepository.findByNativeSQLWithNamedParams("Anderson", "Semedo");

        // Then / Assert
        assertNotNull(personFound);
        assertEquals(savedPerson, personFound);

    }

}
