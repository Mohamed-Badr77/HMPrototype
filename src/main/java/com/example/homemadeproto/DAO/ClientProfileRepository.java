package com.example.homemadeproto.DAO;

import com.example.homemadeproto.entity.ClientProfile;

import com.example.homemadeproto.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    Optional<ClientProfile> findByUtilisateur(Utilisateur utilisateur);
}
