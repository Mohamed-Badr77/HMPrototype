package com.example.homemadeproto.entity;

import enums.MoyensPaiement;
import enums.StatutCommande;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import javax.swing.text.Element;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idC;

    @Min(value = 1, message = "Must at least have one article")
    private int numberOfArticles;

    private double totalPrice;
    private LocalDate dateCommande;

    @Enumerated(EnumType.STRING)
    private StatutCommande statutCommande;

    private int tempsEstimeLivraison;

    private String adresseLivraison;

    @Enumerated(EnumType.STRING)
    private MoyensPaiement moyenPaiement;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="commande_id")
    private List<ElementPanier> elements;

    @PrePersist
    protected void onCreate(){
        this.dateCommande = LocalDate.now();
    }

    public Commande() {
    }

    public Commande(Integer idC, int numberOfArticles, LocalDate dateCommande, float totalPrice, StatutCommande statutCommande, int tempsEstimeLivraison, String adresseLivraison, MoyensPaiement moyenPaiement, List<ElementPanier> elements) {
        this.idC = idC;
        this.numberOfArticles = numberOfArticles;
        this.dateCommande = dateCommande;
        this.totalPrice = totalPrice;
        this.statutCommande = statutCommande;
        this.tempsEstimeLivraison = tempsEstimeLivraison;
        this.adresseLivraison = adresseLivraison;
        this.moyenPaiement = moyenPaiement;
        this.elements = elements;
    }

    public Integer getIdC() {
        return idC;
    }

    public void setIdC(Integer idC) {
        this.idC = idC;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setStatutCommande(StatutCommande statutCommande) {
        this.statutCommande = statutCommande;
    }

    public int getTempsEstimeLivraison() {
        return tempsEstimeLivraison;
    }

    public void setTempsEstimeLivraison(int tempsEstimeLivraison) {
        this.tempsEstimeLivraison = tempsEstimeLivraison;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public List<ElementPanier> getElements() {
        return elements;
    }

    public void setElements(List<ElementPanier> elements) {
        this.elements = elements;
    }
    public MoyensPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyensPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public StatutCommande getStatutCommande() {
        return statutCommande;
    }


}
