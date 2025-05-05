package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}
