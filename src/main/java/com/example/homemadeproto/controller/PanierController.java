package com.example.homemadeproto.controller;


import com.example.homemadeproto.entity.Commande;
import com.example.homemadeproto.entity.ElementPanier;
import com.example.homemadeproto.entity.Panier;
import com.example.homemadeproto.service.CommandeService;
import com.example.homemadeproto.service.ElementPanierService;
import com.example.homemadeproto.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/validate")
    public String validerPanierCreerCommande() {
        Panier cart = panierService.getActivePanier();
        if(cart == null || cart.getElementsPanier() == null || cart.getElementsPanier().isEmpty()) {
            return "redirect:/panier";
        }

        Commande order = new Commande();

    }

}
