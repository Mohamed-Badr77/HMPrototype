package com.example.homemadeproto.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idA;

    private String commentaire;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    private float rating;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @PrePersist
    protected void onCreate(){
        this.creationDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate(){
        if(this.creationDate == null){
            this.creationDate = LocalDate.now();
        }
    }

    public Review() {}
    public Review(float rating, String commentaire) {
        this.rating = rating;
        this.commentaire = commentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getIdA() {
        return idA;
    }

    public void setIdA(long idA) {
        this.idA = idA;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
