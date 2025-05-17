package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Panier;

public interface PanierService{

    Panier getActivePanier(Long userId);
    double calculerTotalPanier(Panier panier);
    void clearCart(Panier cart);
    void addOrIncrementDish(Long dishId, Long userId);


    void decrementDish(Long dishI0,Long userIdd);
}
