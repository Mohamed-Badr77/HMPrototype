package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.ReviewCuisinier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewCuisinierRepository extends JpaRepository<ReviewCuisinier,Long> {
}
