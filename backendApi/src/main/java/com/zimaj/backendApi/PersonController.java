package com.zimaj.backendApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//      Ohne die CrossOrigin Annotation ließ Spring Boot keine Anfragen aus der Angular App zu.
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonController {
    
    @Autowired
    PersonRepository personRepository;

    /*  Es wurden Methoden implementiert, die alle Personen auflisten, nach Vor- oder Nachnamen suchen können, eine Person erstellen, bearbeiten oder löschen oder alle Personen löschen können.
        Diese geben entsprechende HTTP Statusmeldungen zurück.
    */

   @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> person3 = new ArrayList<Person>();

            personRepository.findAll().forEach(person3::add);

            if (person3.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);            
            
            return new ResponseEntity<>(person3, HttpStatus.OK);            
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person/firstname")
    public ResponseEntity<List<Person>> getAllPersonsByfirstName(@RequestParam(required = false) String firstName) {
        try {
            List<Person> person1 = new ArrayList<Person>();

            if (firstName == null)   
                personRepository.findAll().forEach(person1::add);
                           
            else 
                personRepository.findByFirstNameContainingIgnoreCase(firstName).forEach(person1::add);

            if (person1.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);            
            
            return new ResponseEntity<>(person1, HttpStatus.OK);            
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping("/person/lastname")
    public ResponseEntity<List<Person>> getAllPersonsByLastName(@RequestParam(required = false) String lastName) {
        try {
            List<Person> person2 = new ArrayList<Person>();

            if (lastName == null)   
                personRepository.findAll().forEach(person2::add);
                           
            else 
                personRepository.findByLastNameContainingIgnoreCase(lastName).forEach(person2::add);

            if (person2.isEmpty()) 
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);            
            
            return new ResponseEntity<>(person2, HttpStatus.OK);            
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Optional<Person> personId = personRepository.findById(id);

        if (personId.isPresent()) 
            return new ResponseEntity<Person>(personId.get(), HttpStatus.OK);
        else
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person createPerson = personRepository.save(new Person(person.getfirstName(), person.getlastName(), person.getEmail()));
            return new ResponseEntity<>(createPerson, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personId = personRepository.findById(id);

        if (personId.isPresent()) {
            Person changePerson = personId.get();
            changePerson.setfirstName(person.getfirstName());
            changePerson.setlastName(person.getlastName());
            changePerson.setEmail(person.getEmail());

            return new ResponseEntity<>(personRepository.save(changePerson), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/person/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/person")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            personRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
