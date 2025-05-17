package com.example.homemadeproto.controller;


import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import com.example.homemadeproto.entity.Utilisateur;
import com.example.homemadeproto.service.PlatService;
import com.example.homemadeproto.service.ReviewDishService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ReviewDish")
public class ReviewDishController {

    private final ReviewDishService reviewDishService;
    private final PlatService platService;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public ReviewDishController(ReviewDishService reviewDishService, PlatService platService, UtilisateurRepository utilisateurRepository) {
        this.reviewDishService = reviewDishService;
        this.platService = platService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/dish/{dishid}")
    public String getReviewsByDish(HttpSession session, @PathVariable Long dishid, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        Plat plat = platService.getDishById(dishid).orElseThrow(() -> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        List<ReviewDish> reviews = reviewDishService.findByDish(plat);
        model.addAttribute("plat", plat);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);

        return "reviewdish";
    }
    @GetMapping("/dish/{dishid}/add")
    public String showAddReviewForm(HttpSession session, @PathVariable Long dishid, Model model) {
        Plat plat = platService.getDishById(dishid).orElseThrow(() -> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        if(!plat.getCuisinier().getUtilisateur().getUserId().equals(user.getUserId())) {
            ReviewDish reviewDish = new ReviewDish();
            reviewDish.setDish(plat);
            model.addAttribute("reviewDish", reviewDish);
            return "add-reviewdish";
        }else{
            return "unauthorized";
        }

    }

    @PostMapping("/dish/{dishid}/add")
    public String addReview(HttpSession session, @PathVariable Long dishid, @ModelAttribute("review") @Valid ReviewDish reviewDish, BindingResult bindingResult, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        if(bindingResult.hasErrors()) {
            return "add-reviewdish";
        }
        Plat plat = platService.getDishById(dishid).orElseThrow(()-> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        if(plat.getCuisinier().getUtilisateur().getUserId().equals(user.getUserId())) {
           return "unauthorized";
        }else{
            reviewDish.setDish(plat);
            reviewDish.setUtilisateur(user);
            reviewDishService.save(reviewDish);
            return "redirect:/ReviewDish/dish/"+dishid;
        }
        }


    @GetMapping("/edit/{reviewid}")
    public String showEditReviewForm(HttpSession session, @PathVariable Long reviewid, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        ReviewDish review = reviewDishService.findReviewById(reviewid).orElseThrow(()-> new IllegalArgumentException("Invalid review ID : " + reviewid));
        if(!review.getUtilisateur().getUserId().equals(user.getUserId())) {
            return "unauthorized";
        }else{
            model.addAttribute("review", review);
            return "edit-reviewdish";
        }

    }

    @PostMapping("/edit/{reviewid}")

    public String updateReview(HttpSession session, @PathVariable("reviewid") Long reviewid, @ModelAttribute("reviewDish") @Valid ReviewDish review, BindingResult bindingResult) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null) {
            return "redirect:/signin";
        }
        if(bindingResult.hasErrors()) {
            return "edit-reviewdish";
        }
        ReviewDish existingReview = reviewDishService.findReviewById(reviewid).orElseThrow(()-> new IllegalArgumentException("Invalid review ID : " + reviewid));
        if(!existingReview.getUtilisateur().getUserId().equals(user.getUserId())) {
            return "unauthorized";
        }else{
            review.setDish(existingReview.getDish());
            review.setIdA(reviewid);
            reviewDishService.save(review);
            return "redirect:/ReviewDish/dish/"+review.getDish().getId();
        }

    }






}
