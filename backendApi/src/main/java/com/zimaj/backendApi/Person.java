package com.zimaj.backendApi;

import javax.persistence.*;



@Entity
@Table(name="Person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    public Person() {

    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId(){
        return id;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setfirstName(String var) {
        this.firstName = var;
    }

    public void setlastName(String var) {
        this.lastName = var;
    }

    public void setEmail(String var) {
        this.email = var;
    }

    
}
