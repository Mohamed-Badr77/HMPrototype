package com.example.homemadeproto.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idA;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private float rating;

    @PrePersist
    protected void onCreate(){
        this.creationDate = new Date();
    }

    public Review() {}
    public Review(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
