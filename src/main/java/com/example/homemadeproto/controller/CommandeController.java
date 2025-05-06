package com.example.homemadeproto.controller;


import com.example.homemadeproto.entity.Commande;
import com.example.homemadeproto.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Commande")
public class CommandeController {
    private final CommandeService commandeService;
    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/{id}")
    public String showCommande(Model model, @PathVariable int id) {
        Commande order = commandeService.getCommandeById(id).orElseThrow(()-> new IllegalArgumentException("Unavailable id :  "+ id));
        model.addAttribute("commande", order);
        return "commande/voir-commande";
    }

}
