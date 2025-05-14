package com.example.homemadeproto.entity;

import jakarta.persistence.*;

@Entity
public class LivreurProfile {
    @Id
    private Long idLivreur;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    private String modeTransport;
    private float livreurRating = 0.0F;
    private int nombreLivraisons = 0;

    public LivreurProfile() {
    }

    public LivreurProfile(Utilisateur utilisateur, String modeTransport) {
        this.idLivreur = utilisateur.getUserId();
        this.utilisateur = utilisateur;
        this.modeTransport = modeTransport;
    }

    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }

    public float getLivreurRating() {
        return livreurRating;
    }

    public void setLivreurRating(float livreurRating) {
        this.livreurRating = livreurRating;
    }

    public int getNombreLivraisons() {
        return nombreLivraisons;
    }

    public void setNombreLivraisons(int nombreLivraisons) {
        this.nombreLivraisons = nombreLivraisons;
    }
}
