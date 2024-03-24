package com.andsemedodev.restwithspringbootandjava.services;

import com.andsemedodev.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.andsemedodev.restwithspringbootandjava.model.Person;
import com.andsemedodev.restwithspringbootandjava.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServices personServices;

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

    @DisplayName("JUnit test Given Person Objec when Save should Return Person Object")
    @Test
    void testGivenPersonObject_whenSave_shouldReturnPersonObject() {
        // Given / Arrange
        given(personRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(personRepository.save(person)).willReturn(person);

        // When / Act
        Person savedPerson = personServices.create(person);

        // Then / Arrange
        assertNotNull(savedPerson);
        assertEquals("Anderson", savedPerson.getFirstName());

    }

    @DisplayName("JUnit test Given Existing Email when Save Person should Throws Exception")
    @Test
    void testGivenExistingEmail_whenSavePerson_shouldThrowsException() {
        // Given / Arrange
        given(personRepository.findByEmail(anyString())).willReturn(Optional.of(person));

        // When / Act
        assertThrows(ResourceNotFoundException.class, () -> {
            personServices.create(person);
        });

        // Then / Arrange
        verify(personRepository, never()).save(any(Person.class));

    }

    @DisplayName("JUnit test Given Person List when FindAll Person should return list of Person")
    @Test
    void testGivenPersonList_whenFindAll_shouldReturnListOfPerson() {
        // Given / Arrange
        Person person1 = new Person(
                "Isaias",
                "Mendes",
                "Bela Vista - Praia - CV",
                "male",
                "semedoisaias02@gmail.com"
        );

        given(personRepository.findAll()).willReturn(List.of(person, person1));

        // When / Act
        List<Person> personList = personServices.findAll();

        // Then / Arrange
        assertNotNull(personList);
        assertEquals(2, personList.size());


    }

    @DisplayName("JUnit test Given Empty Person List when FindAll Person should return empty list of Person")
    @Test
    void testGivenEmptyPersonList_whenFindAll_shouldReturnEmptyListOfPerson() {
        // Given / Arrange
        given(personRepository.findAll()).willReturn(Collections.emptyList());

        // When / Act
        List<Person> personList = personServices.findAll();

        // Then / Arrange
        assertNotNull(personList);
        assertEquals(0, personList.size());


    }

    @DisplayName("JUnit test Given Person id when FindById should return Person Object")
    @Test
    void testGivenPersonId_whenFindById_shouldReturnPersonObject() {
        // Given / Arrange
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        // When / Act
        Person personFound = personServices.findById(1L);

        // Then / Arrange
        assertNotNull(personFound);
        assertEquals("Anderson", personFound.getFirstName());


    }

    @DisplayName("JUnit test Given Person Object when Update should Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdate_shouldReturnUpdatedPersonObject() {
        // Given / Arrange
        person.setId(1L);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        person.setFirstName("Isaias");
        person.setEmail("semedoisaias02@gmail.com");

        given(personRepository.save(person)).willReturn(person);

        // When / Act
        Person updatedPerson = personServices.update(person);

        // Then / Arrange
        assertNotNull(updatedPerson);
        assertEquals("Isaias", updatedPerson.getFirstName());

    }

    @DisplayName("JUnit test Given Person Id when Delete should Do Nothing")
    @Test
    void testGivenPersonId_whenDelete_shouldDoNothing() {
        // Given / Arrange
        person.setId(1L);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));
        willDoNothing().given(personRepository).delete(person);

        // When / Act
        personServices.delete(person.getId());

        // Then / Arrange
        verify(personRepository, times(1)).delete(person);

    }

}
