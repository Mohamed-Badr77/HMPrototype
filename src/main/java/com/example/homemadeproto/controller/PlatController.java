package com.example.homemadeproto.controller;

import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.service.PlatService;
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

    @Autowired
    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    @GetMapping
    public String listPlats(Model model) {
        List<Plat> plats = platService.getAllDishesWithRatings();
        model.addAttribute("dishes", plats);
        return "dishes";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("plat", new Plat());
        return "add-dish";
    }

    @PostMapping("/add")
    public String addPlat(@Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-dish";
        }
        platService.saveDish(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Plat plat = platService.getDishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dish ID: " + id));
        model.addAttribute("plat", plat);
        return "edit-dish";
    }

    @PostMapping("/edit/{id}")
    public String updatePlat(@PathVariable Long id, @Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-dish";
        }
        plat.setId(id);
        platService.saveDish(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Plat plat = platService.getDishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dish ID: " + id));
        model.addAttribute("plat", plat);
        return "delete-dish";
    }

    @PostMapping("/delete/{id}")
    public String deletePlat(@PathVariable Long id) {
        platService.deleteDish(id);
        return "redirect:/dishes";
    }
}
