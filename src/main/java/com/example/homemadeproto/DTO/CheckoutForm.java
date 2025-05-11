package com.example.homemadeproto.DTO;

import enums.MoyensPaiement;

public class CheckoutForm {
    private String adresseLivraison;
    private MoyensPaiement moyenPaiement;

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public MoyensPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyensPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }
}
