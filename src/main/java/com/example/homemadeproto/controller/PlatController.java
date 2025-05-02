package com.example.homemadeproto.controller;


import com.example.homemadeproto.model.Plat;
import jakarta.validation.Valid;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.homemadeproto.repository.PlatRepository;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class PlatController {
    private final PlatRepository platRepository;

    public PlatController(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    @GetMapping
    public String listPlats(Model model) {
        List<Plat> plats = platRepository.findAll();
        model.addAttribute("dishes",platRepository.findAll());
        return "dishes";

    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("plat", new Plat());
        return "add-dish";
    }

    @PostMapping("/add")
    public String addPlat(@Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-dish";
        }
        platRepository.save(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Plat plat = platRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid dish ID: " + id));
        model.addAttribute("plat", plat);
        return "edit-dish";
    }

    @PostMapping("/edit/{id}")
    public String updatePlat(@PathVariable Long id,@Valid @ModelAttribute("plat") Plat plat, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit-dish";
        }
        plat.setId(id);
        platRepository.save(plat);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Plat plat = platRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid dish ID: " + id));
        model.addAttribute("plat", plat);
        return "delete-dish";
    }

    @PostMapping("/delete/{id}")
    public String deletePlat(@PathVariable Long id) {
        platRepository.deleteById(id);
        return "redirect:/dishes";
    }

}
