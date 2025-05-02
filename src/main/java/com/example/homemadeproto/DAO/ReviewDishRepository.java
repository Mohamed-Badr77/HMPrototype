package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDishRepository extends JpaRepository<ReviewDish, Long> {
    List<ReviewDish> findByDishId(long id);
    List<ReviewDish> findByDish(Plat plat);

}
