package com.example.homemadeproto.entity;

import jakarta.persistence.*;
import org.springframework.beans.PropertyValues;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanier;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementPanier> elementsPanier = new ArrayList<>();

    private float fraisLivraison;

    public Long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Long idPanier) {
        this.idPanier = idPanier;
    }

    public List<ElementPanier> getElementsPanier() {
        return elementsPanier;
    }

    public void setElementsPanier(List<ElementPanier> elementPanier) {
        this.elementsPanier = elementPanier;
    }

    public float getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(float fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

}
