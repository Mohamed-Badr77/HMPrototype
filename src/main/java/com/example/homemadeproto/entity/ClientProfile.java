package com.example.homemadeproto.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientProfile {

    @Id
    private Long idClient;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    private String adresseClient;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Commande> commandes;

    @OneToOne(cascade = CascadeType.ALL)
    private Panier panier;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public ClientProfile() {
    }

    public ClientProfile(Long idClient, String adresseClient, List<Commande> commandes) {
        this.idClient = idClient;
        this.adresseClient = adresseClient;
        this.commandes = commandes;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
