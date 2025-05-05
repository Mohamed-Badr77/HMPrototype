package com.example.homemadeproto.entity;

import jakarta.persistence.*;

@Entity
public class ElementPanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEP;

    private Plat plat;

    private Integer Quantite;

    @ManyToOne
    private Commande commande;

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Long getIdEP() {
        return idEP;
    }

    public void setIdEP(Long idEP) {
        this.idEP = idEP;
    }

    public Integer getQuantite() {
        return Quantite;
    }

    public void setQuantite(Integer quantite) {
        Quantite = quantite;
    }
}
