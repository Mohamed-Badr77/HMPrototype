package com.example.homemadeproto.controller;


import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import com.example.homemadeproto.service.PlatService;
import com.example.homemadeproto.service.ReviewDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getReviewsByDish(@PathVariable Long dishId,Model model){
        Plat plat = platService.getDishById(dishId).orElseThrow(()->new IllegalArgumentException("Invalid Dish Id  :" + dishId));
        List<ReviewDish> reviews = reviewDishService.findByDish(plat);
        model.addAttribute("reviews", reviews);
        model.addAttribute("plat", plat);
        return "reviewdish";
    }



}
