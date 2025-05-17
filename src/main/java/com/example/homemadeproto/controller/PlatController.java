package com.example.homemadeproto.controller;

import com.example.homemadeproto.DAO.CuisinierProfileRepository;
import com.example.homemadeproto.entity.CuisinierProfile;
import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.Utilisateur;
import com.example.homemadeproto.service.PlatService;
import enums.Role;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class PlatController {

    private final PlatService platService;
    private final CuisinierProfileRepository cuisinierProfileRepository;

    @Autowired
    public PlatController(PlatService platService, CuisinierProfileRepository cuisinierProfileRepository) {

        this.platService = platService;
        this.cuisinierProfileRepository = cuisinierProfileRepository;
    }

    @GetMapping
    public String listPlats(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        List<Plat> plats = platService.getAllDishesWithRatings();
        model.addAttribute("dishes", plats);
        model.addAttribute("currentUser", user);
        return "dishes";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        if(user.hasRole(Role.CUISINIER)) {
            model.addAttribute("plat", new Plat());
            return "add-dish";
        }else{
            return "redirect:/unauthorized";
        }
    }

    @PostMapping("/add")
    public String addPlat(HttpSession session, @Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        if(!user.hasRole(Role.CUISINIER) || user.getCuisinierProfile() == null) {
            return "redirect:/unauthorized";
        }
        if (bindingResult.hasErrors()) {
            return "add-dish";
        }

        Long cuisinierProfileId = user.getCuisinierProfile().getIdCuisinier();
        CuisinierProfile managedProfile = cuisinierProfileRepository.findById(cuisinierProfileId).orElse(null);
        if(managedProfile == null) {
            return "redirect:/unauthorized";
        }
        plat.setCuisinier(managedProfile);
        platService.saveDish(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(HttpSession session, @PathVariable Long id, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        Plat plat = platService.getDishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dish ID: " + id));

        CuisinierProfile dishOwner = plat.getCuisinier();
        if(!dishOwner.getUtilisateur().getUserId().equals(user.getUserId())) {
            return "redirect:/unauthorized";
        }
        model.addAttribute("plat", plat);
        return "edit-dish";
    }

    @PostMapping("/edit/{id}")
    public String updatePlat(HttpSession session, @PathVariable Long id, @Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }

        Plat existingPlat = platService.getDishById(id).orElseThrow(()-> new IllegalArgumentException("Invalid dish ID: " + id));
        if (bindingResult.hasErrors()) {
            return "edit-dish";
        }
        plat.setId(id);
        plat.setCuisinier(existingPlat.getCuisinier());
        platService.saveDish(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(HttpSession session, @PathVariable Long id, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }


        Plat plat = platService.getDishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dish ID: " + id));
        CuisinierProfile dishOwner = plat.getCuisinier();
        if(!dishOwner.getUtilisateur().getUserId().equals(user.getUserId())) {
            return "redirect:/unauthorized";
        }else{
            model.addAttribute("plat", plat);
            return "delete-dish";
        }
    }

    @PostMapping("/delete/{id}")
    public String deletePlat(HttpSession session, @PathVariable Long id) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        Plat plat = platService.getDishById(id).orElseThrow(()-> new IllegalArgumentException("Invalid dish ID: " + id));
        if(!plat.getCuisinier().getUtilisateur().getUserId().equals(user.getUserId())) {
            return "redirect:/unauthorized";
        }
        platService.deleteDish(id);
        return "redirect:/dishes";
    }
}
