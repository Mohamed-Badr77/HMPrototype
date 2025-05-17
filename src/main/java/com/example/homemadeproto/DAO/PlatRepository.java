package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.CuisinierProfile;
import com.example.homemadeproto.entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    List<Plat> findByCuisinier(CuisinierProfile cuisinier);

}
