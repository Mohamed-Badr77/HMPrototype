package com.example.homemadeproto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReviewLivreur extends Review {

    @ManyToOne(optional = false)
    @JoinColumn(name = "livreur_idl", nullable = false)
    private LivreurProfile livreurProfile;

    private float tempsLivraison;
    private float politesse;
    private float etatLivrable;
    private float trackingAccuracy;

    public ReviewLivreur() {
    }

    public ReviewLivreur(float rating, String commentaire, LivreurProfile livreurProfile, float tempsLivraison, float trackingAccuracy, float politesse, float etatLivrable) {
        super(rating, commentaire);
        this.livreurProfile = livreurProfile;
        this.tempsLivraison = tempsLivraison;
        this.trackingAccuracy = trackingAccuracy;
        this.politesse = politesse;
        this.etatLivrable = etatLivrable;
    }

    public LivreurProfile getLivreurProfile() {
        return livreurProfile;
    }

    public void setLivreurProfile(LivreurProfile livreurProfile) {
        this.livreurProfile = livreurProfile;
    }

    public float getTempsLivraison() {
        return tempsLivraison;
    }

    public void setTempsLivraison(float tempsLivraison) {
        this.tempsLivraison = tempsLivraison;
    }

    public float getPolitesse() {
        return politesse;
    }

    public void setPolitesse(float politesse) {
        this.politesse = politesse;
    }

    public float getEtatLivrable() {
        return etatLivrable;
    }

    public void setEtatLivrable(float etatLivrable) {
        this.etatLivrable = etatLivrable;
    }

    public float getTrackingAccuracy() {
        return trackingAccuracy;
    }

    public void setTrackingAccuracy(float trackingAccuracy) {
        this.trackingAccuracy = trackingAccuracy;
    }
}
