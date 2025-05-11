package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    List<LigneCommande> findByCommandeIdC(int idC);
}
