package com.example.homemadeproto.controller;


import com.example.homemadeproto.DTO.CheckoutForm;
import com.example.homemadeproto.entity.Commande;
import com.example.homemadeproto.entity.ElementPanier;
import com.example.homemadeproto.entity.Panier;
import com.example.homemadeproto.service.CommandeService;
import com.example.homemadeproto.service.ElementPanierService;
import com.example.homemadeproto.service.PanierService;
import enums.MoyensPaiement;
import enums.StatutCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/panier")
public class PanierController {
    private final PanierService panierService;
    private final ElementPanierService elementPanierService;
    private final CommandeService commandeService;

    @Autowired
    public PanierController(PanierService panierService, ElementPanierService elementPanierService, CommandeService commandeService) {
        this.panierService = panierService;
        this.elementPanierService = elementPanierService;
        this.commandeService = commandeService;
    }

    @GetMapping
    public String showPanier(Model model) {
        Panier cart = panierService.getActivePanier();
        List<ElementPanier> elements = cart.getElementsPanier();

        double total = elements.stream().mapToDouble(e->e.getPlat().getPrice()*e.getQuantite()).sum();

        model.addAttribute("total", total);
        model.addAttribute("elements", elements);
        model.addAttribute("cart", cart);
        return "panier/voir-panier";
    }
    @PostMapping("/increment")
    public String addToCart(@RequestParam("dishId") Long dishId) {
        panierService.addOrIncrementDish(dishId);
        return "redirect:/panier";
    }

    @PostMapping("/decrement")
    public String decrementFromCart(@RequestParam("dishId") Long dishId) {
        Panier cart = panierService.getActivePanier();
        if(cart!= null){
            panierService.decrementDish(dishId);
        }
        return "redirect:/panier";
    }

    @GetMapping("/checkout")
    public String afficherCheckoutPanier(Model model) {
        Panier cart = panierService.getActivePanier();
        if(cart == null || cart.getElementsPanier() == null || cart.getElementsPanier().isEmpty()) {
            return "redirect:/panier";
        }

        model.addAttribute("checkoutForm", new CheckoutForm());
        model.addAttribute("elements", cart.getElementsPanier());
        model.addAttribute("total", panierService.calculerTotalPanier(cart));
        model.addAttribute("moyens", MoyensPaiement.values());
        return "panier/checkout";
    }

    @PostMapping("/checkout")
    public String traiterSoumissionCheckoutPanier(@ModelAttribute CheckoutForm checkoutForm) {
        Panier cart = panierService.getActivePanier();
        if(cart == null || cart.getElementsPanier() == null || cart.getElementsPanier().isEmpty()) {
            return "redirect:/panier";
        }

        Commande order = new Commande();
        order.setElements(new ArrayList<>(cart.getElementsPanier()));
        order.setTotalPrice(panierService.calculerTotalPanier(cart));
        order.setStatutCommande(StatutCommande.EN_COURS);
        order.setAdresseLivraison(checkoutForm.getAdresseLivraison());
        order.setMoyenPaiement(checkoutForm.getMoyenPaiement());
        order.setNumberOfArticles(cart.getElementsPanier().size());

        commandeService.saveCommande(order);
        panierService.clearCart(cart);

        return "redirect:/commande/" + order.getIdC();
    }
}
