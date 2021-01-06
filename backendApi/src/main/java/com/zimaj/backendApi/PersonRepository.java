package com.zimaj.backendApi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
   // Zwei Methoden, die nach Vor- und Nachnamen suchen können, alle anderen Standardmethoden sind in dem JPA Repository definiert.
   List<Person> findByFirstNameContainingIgnoreCase(String firstName);
   List<Person> findByLastNameContainingIgnoreCase(String lastName);

  
}
