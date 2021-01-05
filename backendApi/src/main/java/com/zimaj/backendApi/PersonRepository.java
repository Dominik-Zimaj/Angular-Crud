package com.zimaj.backendApi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
   List<Person> findByFirstNameContainingIgnoreCase(String firstName);
   List<Person> findByLastNameContainingIgnoreCase(String lastName);

  
}
