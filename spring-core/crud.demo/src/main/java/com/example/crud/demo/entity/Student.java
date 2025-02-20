package com.example.crud.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name ="student")

public class Student {

    //DEFINE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
    int id =0;


    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private  String lastName;


    @Column(name="email")
    private String email;

    //define constracters
    public Student(){

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    //define getters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName =  LastName;
    }

    public String getEmail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }


    //define tostring() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", Firstname='" + firstName + '\'' +
                ",LastName='" + lastName + '\'' +
                ", Email=" + email+
                '}';
    }
}
