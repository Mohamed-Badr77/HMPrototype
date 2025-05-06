package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Panier;

public interface PanierService{

    Panier getActivePanier();
    double calculerTotalPanier(Panier panier);
    void clearCart(Panier cart);
    void addOrIncrementDish(Long dishId);

    void decrementDish(Long dishId);
}
