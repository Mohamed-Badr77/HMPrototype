package com.example.homemadeproto.entity;


import jakarta.persistence.*;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLC;

    private String nomPlat;
    private int quantite;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public long getIdLC() {
        return idLC;
    }

    public void setIdLC(long idLC) {
        this.idLC = idLC;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
