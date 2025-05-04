package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;

import java.util.List;
import java.util.Optional;

public interface ReviewDishService {
    ReviewDish save(ReviewDish review);
    List<ReviewDish> findByDishId(Long dishId);
    List<ReviewDish> findByDish(Plat plat);
    Optional<ReviewDish> findReviewById(Long reviewId);

}
