package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.CuisinierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisinierProfileRepository extends JpaRepository<CuisinierProfile, Long> {
}
