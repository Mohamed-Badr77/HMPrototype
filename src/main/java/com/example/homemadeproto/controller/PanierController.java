package com.example.homemadeproto.controller;


import com.example.homemadeproto.DTO.CheckoutForm;
import com.example.homemadeproto.entity.*;
import com.example.homemadeproto.service.CommandeService;
import com.example.homemadeproto.service.ElementPanierService;
import com.example.homemadeproto.service.PanierService;
import enums.MoyensPaiement;
import enums.StatutCommande;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showPanier(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        Panier cart = panierService.getActivePanier(user.getUserId());
        List<ElementPanier> elements = cart.getElementsPanier();

        double total = elements.stream().mapToDouble(e->e.getPlat().getPrice()*e.getQuantite()).sum();

        model.addAttribute("total", total);
        model.addAttribute("elements", elements);
        model.addAttribute("cart", cart);
        return "panier/voir-panier";
    }
    @PostMapping("/increment")
    public String addToCart(HttpSession session, @RequestParam("dishId") Long dishId) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user==null){
            return "redirect:/signin";
        }
        panierService.addOrIncrementDish(dishId, user.getUserId());
        return "redirect:/panier";
    }

    @PostMapping("/decrement")
    public String decrementFromCart(HttpSession session, @RequestParam("dishId") Long dishId) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user==null){
            return "redirect:/signin";
        }
        Panier cart = panierService.getActivePanier(user.getUserId());
        if(cart!= null){
            panierService.decrementDish(dishId,user.getUserId());
        }
        return "redirect:/panier";
    }

    @GetMapping("/checkout")
    public String afficherCheckoutPanier(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user==null){
            return "redirect:/signin";
        }
        Panier cart = panierService.getActivePanier(user.getUserId());
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
    public String traiterSoumissionCheckoutPanier(HttpSession session, @ModelAttribute CheckoutForm checkoutForm) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user==null){
            return "redirect:/signin";
        }
        Panier cart = panierService.getActivePanier(user.getUserId());
        if(cart == null || cart.getElementsPanier() == null || cart.getElementsPanier().isEmpty()) {
            return "redirect:/panier";
        }
        Commande order = new Commande();
        List<LigneCommande> lignesCommande = cart.getElementsPanier().stream().map(ep -> {
            LigneCommande ligne = new LigneCommande();
            ligne.setNomPlat(ep.getPlat().getDishName());
            ligne.setQuantite(ep.getQuantite());
            ligne.setPrix(ep.getPlat().getPrice());
            ligne.setCommande(order);
            return ligne;
        }).toList();
        order.setLignesCommande(lignesCommande);
        order.setTotalPrice(panierService.calculerTotalPanier(cart));
        order.setStatutCommande(StatutCommande.EN_COURS);
        order.setAdresseLivraison(checkoutForm.getAdresseLivraison());
        order.setMoyenPaiement(checkoutForm.getMoyenPaiement());
        order.setNumberOfArticles(cart.getElementsPanier().size());
        order.setClient(user.getClientProfile());

        commandeService.saveCommande(order);
        panierService.clearCart(cart);

        return "redirect:/commande/" + order.getIdC();
    }
}
