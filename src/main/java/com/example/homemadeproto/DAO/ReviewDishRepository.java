package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.Plat;
import com.example.homemadeproto.entity.ReviewDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDishRepository extends JpaRepository<ReviewDish, Long> {
    List<ReviewDish> findByDishId(Long id);
    List<ReviewDish> findByDish(Plat plat);
    @Query("SELECT AVG(r.rating) FROM ReviewDish r WHERE r.dish = :dish")
    Double findAverageRatingByDish(@Param("dish") Plat plat);



}
