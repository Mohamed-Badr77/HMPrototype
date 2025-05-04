package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Plat;
import java.util.List;
import java.util.Optional;

public interface PlatService {
    List<Plat> getAllDishes();

    Optional<Plat> getDishById(Long id);

    Plat saveDish(Plat plat);

    void deleteDish(Long id);

    List<Plat> getAllDishesWithRatings();
}