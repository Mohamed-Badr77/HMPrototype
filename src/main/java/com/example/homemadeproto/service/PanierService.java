package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Panier;

public interface PanierService{

    public Panier getActivePanier();
    public double calculerTotalPanier(Panier panier);
    public void clearCart(Panier cart);
}
