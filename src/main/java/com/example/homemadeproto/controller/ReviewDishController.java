package com.example.homemadeproto.controller;


import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import com.example.homemadeproto.service.PlatService;
import com.example.homemadeproto.service.ReviewDishService;
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

    @Autowired
    public ReviewDishController(ReviewDishService reviewDishService, PlatService platService) {
        this.reviewDishService = reviewDishService;
        this.platService = platService;
    }

    @GetMapping("/dish/{dishid}")
    public String getReviewsByDish(@PathVariable Long dishid, Model model) {
        Plat plat = platService.getDishById(dishid).orElseThrow(() -> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        List<ReviewDish> reviews = reviewDishService.findByDish(plat);
        model.addAttribute("plat", plat);
        model.addAttribute("reviews", reviews);

        return "reviewdish";
    }
    @GetMapping("/dish/{dishid}/add")
    public String showAddReviewForm(@PathVariable Long dishid,Model model) {
        Plat plat = platService.getDishById(dishid).orElseThrow(() -> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        ReviewDish reviewDish = new ReviewDish();
        reviewDish.setDish(plat);
        model.addAttribute("reviewDish", reviewDish);
        return "add-reviewdish";
    }

    @PostMapping("/dish/{dishid}/add")
    public String addReview(@PathVariable Long dishid, @ModelAttribute("review") @Valid ReviewDish reviewDish, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "add-reviewdish";
        }
        Plat plat = platService.getDishById(dishid).orElseThrow(()-> new IllegalArgumentException("Invalid Dish Id  :" + dishid));
        reviewDish.setDish(plat);
        reviewDishService.save(reviewDish);
        return "redirect:/ReviewDish/dish/"+dishid;
    }

    @GetMapping("/edit/{reviewid}")
    public String showEditReviewForm(@PathVariable Long reviewid, Model model) {
        ReviewDish review = reviewDishService.findReviewById(reviewid).orElseThrow(()-> new IllegalArgumentException("Invalid review ID : " + reviewid));
        model.addAttribute("review", review);
        return "edit-reviewdish";
    }

    @PostMapping("/edit/{reviewid}")

    public String updateReview(@PathVariable("reviewid") Long reviewid, @ModelAttribute("review") @Valid ReviewDish review, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit-reviewdish";
        }
        ReviewDish existingReview = reviewDishService.findReviewById(reviewid).orElseThrow(()-> new IllegalArgumentException("Invalid review ID : " + reviewid));
        review.setDish(existingReview.getDish());
        review.setIdA(reviewid);
        reviewDishService.save(review);
        return "redirect:/ReviewDish/dish/"+review.getDish().getId();
    }






}
