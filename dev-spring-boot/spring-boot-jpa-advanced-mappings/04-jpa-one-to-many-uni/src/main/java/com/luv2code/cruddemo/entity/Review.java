package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Review",schema = "hb_04_one_to_many_uni")
public class Review {

    // define fields

    // define constructors

    // define getter/ setter

    // define tosString

    //annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "comment")
    private String comment;

    public Review (){

    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
