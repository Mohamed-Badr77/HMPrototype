package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.PanierRepository;
import com.example.homemadeproto.DAO.PlatRepository;
import com.example.homemadeproto.entity.ElementPanier;
import com.example.homemadeproto.entity.Panier;
import com.example.homemadeproto.entity.Plat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PlatRepository platRepository;
    private final ElementPanierService elementPanierService;

    @Autowired
    public PanierServiceImpl(PanierRepository panierRepository, PlatRepository platRepository, ElementPanierService elementPanierService) {
        this.panierRepository = panierRepository;
        this.platRepository = platRepository;
        this.elementPanierService = elementPanierService;
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

    @Override
    public void addOrIncrementDish(Long dishId) {
        Plat dish = platRepository.findById(dishId).orElseThrow(()-> new RuntimeException("Invalid dish id : " + dishId));

        Panier cart = getActivePanier();

        Optional<ElementPanier> existing = cart.getElementsPanier().stream().filter(e -> e.getPlat().getId().equals(dishId)).findFirst();
        if(existing.isPresent()){
            ElementPanier elementPanier = existing.get();
            elementPanier.setQuantite(elementPanier.getQuantite() + 1);
        }else {
            ElementPanier newElement = new ElementPanier();
            newElement.setPlat(dish);
            newElement.setQuantite(1);
            newElement.setPanier(cart);
            List<ElementPanier> NewList = cart.getElementsPanier();
            NewList.add(newElement);
            cart.setElementsPanier(NewList);
        }
        panierRepository.save(cart);
    }

    @Override
    public void decrementDish(Long dishId) {
        Panier cart = getActivePanier();
        if(cart.getElementsPanier() == null) return;
        List<ElementPanier> elements= cart.getElementsPanier();
        ElementPanier toDecrement = elements.stream().filter(e -> e.getPlat().getId() == dishId).findFirst().orElse(null);
        if(toDecrement != null){
            if(toDecrement.getQuantite()>1){
                toDecrement.setQuantite(toDecrement.getQuantite() - 1);
                elementPanierService.saveElementPanier(toDecrement);
            }else{
                elementPanierService.deleteElementPanier(toDecrement);
                elements.remove(toDecrement);
            }
        }
    }
}
