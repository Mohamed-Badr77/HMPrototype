package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;

import java.util.List;

public interface ReviewDishService {
    ReviewDish save(ReviewDish review);
    List<ReviewDish> findByDishId(int dishId);
    List<ReviewDish> findByDish(Plat plat);

}
