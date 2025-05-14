package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.ReviewLivreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLivreurRepository extends JpaRepository<ReviewLivreur, Long> {
}
