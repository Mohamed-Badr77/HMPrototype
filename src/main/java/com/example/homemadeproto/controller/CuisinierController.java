package com.example.homemadeproto.controller;


import com.example.homemadeproto.DAO.CuisinierProfileRepository;
import com.example.homemadeproto.DAO.PlatRepository;
import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.entity.CuisinierProfile;
import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cuisinier")
public class CuisinierController {

    private CuisinierProfileRepository cuisinierProfileRepository;
    private UtilisateurRepository utilisateurRepository;
    private PlatRepository platRepository;

    @Autowired
    public CuisinierController(CuisinierProfileRepository cuisinierProfileRepository,PlatRepository platRepository, UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.cuisinierProfileRepository = cuisinierProfileRepository;
        this.platRepository = platRepository;
    }

    @GetMapping("/{userId}")
    public String afficherDetailsCuisinier(@PathVariable Long userId,Model model) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        if(utilisateur == null || utilisateur.getCuisinierProfile() == null) {
            return "redirect:/unauthorized";
        }

        CuisinierProfile cuisinier = utilisateur.getCuisinierProfile();
        List<Plat> plats = platRepository.findByCuisinier(cuisinier);
        model.addAttribute("cuisinier", cuisinier);
        model.addAttribute("plats", plats);

        return "cuisinier-details";


    }
}
