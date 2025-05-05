package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.PanierRepository;
import com.example.homemadeproto.entity.Panier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;

    @Autowired
    public PanierServiceImpl(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public Panier getActivePanier(){
        return panierRepository.findAll().stream().findFirst().orElseGet(() -> {
            Panier nvPanier = new Panier();
            return panierRepository.save(nvPanier);
        });
    }

    public double calculerTotalPanier(Panier panier){
        return panier.getElementsPanier().stream().mapToDouble(e-> e.getPlat().getPrice() * e.getQuantite()).sum();
    }

    public void clearCart(Panier cart){
        cart.getElementsPanier().clear();
        panierRepository.save(cart);
    }
}
