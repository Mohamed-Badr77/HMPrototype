package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.PlatRepository;
import com.example.homemadeproto.DAO.ReviewDishRepository;
import com.example.homemadeproto.entity.CuisinierProfile;
import com.example.homemadeproto.entity.Plat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatServiceImpl implements PlatService {

    private final PlatRepository platRepository;
    private final ReviewDishRepository reviewDishRepository;

    @Autowired
    public PlatServiceImpl(PlatRepository platRepository, ReviewDishRepository reviewDishRepository) {
        this.platRepository = platRepository;
        this.reviewDishRepository = reviewDishRepository;
    }


    @Override
    public List<Plat> getAllDishes() {
        return platRepository.findAll();
    }

    @Override
    public Optional<Plat> getDishById(Long id) {
        return platRepository.findById(id);
    }

    @Override
    public Plat saveDish(Plat plat) {
        return platRepository.save(plat);
    }

    @Override
    public void deleteDish(Long id) {
        platRepository.deleteById(id);
    }

    @Override
    public List<Plat> getAllDishesWithRatings() {
        List<Plat> dishes = platRepository.findAll();
        for (Plat dish : dishes) {
            Double avg = reviewDishRepository.findAverageRatingByDish(dish);
            dish.setDishRating(avg!=null ? avg.floatValue() : 0f);
        }
        return dishes;
    }

    @Override
    public List<Plat> findDishesByCuisinier(CuisinierProfile cuisinierProfile){
        return platRepository.findByCuisinier(cuisinierProfile);
    }
}
