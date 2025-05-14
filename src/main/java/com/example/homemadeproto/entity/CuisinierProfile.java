package com.example.homemadeproto.entity;


import jakarta.persistence.*;

@Entity
public class CuisinierProfile {

    @Id
    private Long idCuisinier;

    @OneToOne
    @MapsId
    @JoinColumn(name= "user_id")
    private Utilisateur utilisateur;

    private String adresseCuisinier;
    private Boolean estVerfie = false;
    private float ratingCuisinier = 0.0F;
    private String specialiteCulinaire;
    private float gains = 0.0F;

    public CuisinierProfile() {
    }

    public CuisinierProfile(Long idCuisinier, Utilisateur utilisateur, String adresseCuisinier, String specialiteCulinaire) {
        this.idCuisinier = idCuisinier;
        this.utilisateur = utilisateur;
        this.adresseCuisinier = adresseCuisinier;
        this.specialiteCulinaire = specialiteCulinaire;

    }

    public Long getIdCuisinier() {
        return idCuisinier;
    }

    public void setIdCuisinier(Long idCuisinier) {
        this.idCuisinier = idCuisinier;
    }

    public float getGains() {
        return gains;
    }

    public void setGains(float gains) {
        this.gains = gains;
    }

    public String getSpecialiteCulinaire() {
        return specialiteCulinaire;
    }

    public void setSpecialiteCulinaire(String specialiteCulinaire) {
        this.specialiteCulinaire = specialiteCulinaire;
    }

    public String getAdresseCuisinier() {
        return adresseCuisinier;
    }

    public void setAdresseCuisinier(String adresseCuisinier) {
        this.adresseCuisinier = adresseCuisinier;
    }

    public Boolean getEstVerfie() {
        return estVerfie;
    }

    public void setEstVerfie(Boolean estVerfie) {
        this.estVerfie = estVerfie;
    }

    public float getRatingCuisinier() {
        return ratingCuisinier;
    }

    public void setRatingCuisinier(float ratingCuisinier) {
        this.ratingCuisinier = ratingCuisinier;
    }
}
