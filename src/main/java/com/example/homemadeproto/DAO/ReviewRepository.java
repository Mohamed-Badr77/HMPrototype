package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
