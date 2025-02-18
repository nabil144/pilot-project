package com.example.bookingapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "person")
    private String person;

    @Column(name = "book")
    private String book;

    public Booking(){

    }

    public Booking(String member, String book) {
        this.person = person;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}
