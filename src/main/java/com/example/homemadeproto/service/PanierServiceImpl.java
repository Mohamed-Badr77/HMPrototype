package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.PanierRepository;
import com.example.homemadeproto.DAO.PlatRepository;
import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.entity.ClientProfile;
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
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public PanierServiceImpl(PanierRepository panierRepository, PlatRepository platRepository, ElementPanierService elementPanierService, UtilisateurRepository utilisateurRepository) {
        this.panierRepository = panierRepository;
        this.platRepository = platRepository;
        this.elementPanierService = elementPanierService;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Panier getActivePanier(Long userId) {
        Panier panier = panierRepository.findPanierByClient(utilisateurRepository.findById(userId).get().getClientProfile());
        if (panier == null) {
            Panier nvPanier = new Panier();
            nvPanier.setClient(utilisateurRepository.findById(userId).get().getClientProfile());
            return panierRepository.save(nvPanier);
        }
        return panier;
    }

    public double calculerTotalPanier(Panier panier){
        return panier.getElementsPanier().stream().mapToDouble(e-> e.getPlat().getPrice() * e.getQuantite()).sum();
    }

    public void clearCart(Panier cart){
        cart.getElementsPanier().clear();
        panierRepository.save(cart);
    }

    @Override
    public void addOrIncrementDish(Long dishId, Long userId) {
        Plat dish = platRepository.findById(dishId).orElseThrow(()-> new RuntimeException("Invalid dish id : " + dishId));

        Panier cart = getActivePanier(userId);

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
    public void decrementDish(Long dishId,Long userId) {
        Panier cart = getActivePanier(userId);
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
