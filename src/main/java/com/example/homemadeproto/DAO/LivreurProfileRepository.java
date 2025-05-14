package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.LivreurProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurProfileRepository extends JpaRepository<LivreurProfile, Long> {
}
