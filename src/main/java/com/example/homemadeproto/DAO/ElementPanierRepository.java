package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.ElementPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementPanierRepository extends JpaRepository<ElementPanier,Long> {
}
