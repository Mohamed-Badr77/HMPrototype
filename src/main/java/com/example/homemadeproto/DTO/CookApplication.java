package com.example.homemadeproto.DTO;

import jakarta.validation.constraints.NotBlank;

public class CookApplication {
    @NotBlank(message = "L'adresse ne peut pas être vide")
    private String adresse;

    @NotBlank(message = "La spécialité ne peut pas être vide")
    private String specialite;

    public CookApplication() {
    }

    public CookApplication(String adresse, String specialite) {
        this.adresse = adresse;
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
