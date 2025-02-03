package com.example.crud.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name ="TASK")

public class Student {

    //DEFINE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
    int id =0;



    @Column(name="name")
    private String name;



    @Column(name="age")
    private  String age;



    @Column(name="phone")
    private int phone;

    //define constracters
    public Student(){

    }

    public Student(String name, String age, int phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    //define getters and getters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


    //define tostring() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone=" + phone +
                '}';
    }
}
