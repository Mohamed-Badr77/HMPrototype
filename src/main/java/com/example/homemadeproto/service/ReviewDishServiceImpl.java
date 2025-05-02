package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.ReviewDishRepository;
import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewDishServiceImpl implements ReviewDishService{
    private final ReviewDishRepository reviewDishRepository;

    @Autowired
    public ReviewDishServiceImpl(ReviewDishRepository reviewDishRepository) {
        this.reviewDishRepository = reviewDishRepository;
    }

    @Override
    public ReviewDish save(ReviewDish review) {
        return reviewDishRepository.save(review);
    }

    @Override
    public List<ReviewDish> findByDishId(int dishId) {
        return reviewDishRepository.findByDishId(dishId);
    }

    @Override
    public List<ReviewDish> findByDish(Plat plat) {
        return reviewDishRepository.findByDish(plat);
    }
}
