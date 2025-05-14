package com.example.homemadeproto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReviewCuisinier extends Review {

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuisinier_idc", nullable = false)
    private CuisinierProfile cuisinierProfile;

    private float communicationRating;
    private float vitesseReponse;
    private float hygiene;

    public ReviewCuisinier() {
    }

    public ReviewCuisinier(float rating, String commentaire ,CuisinierProfile cuisinierProfile, float communicationRating, float vitesseReponse, float hygiene) {
        super(rating,commentaire);
        this.cuisinierProfile = cuisinierProfile;
        this.communicationRating = communicationRating;
        this.vitesseReponse = vitesseReponse;
        this.hygiene = hygiene;
    }

    public CuisinierProfile getCuisinierProfile() {
        return cuisinierProfile;
    }

    public void setCuisinierProfile(CuisinierProfile cuisinierProfile) {
        this.cuisinierProfile = cuisinierProfile;
    }

    public float getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(float communicationRating) {
        this.communicationRating = communicationRating;
    }

    public float getVitesseReponse() {
        return vitesseReponse;
    }

    public void setVitesseReponse(float vitesseReponse) {
        this.vitesseReponse = vitesseReponse;
    }

    public float getHygiene() {
        return hygiene;
    }

    public void setHygiene(float hygiene) {
        this.hygiene = hygiene;
    }
}
