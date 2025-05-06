package com.example.homemadeproto.service;

import com.example.homemadeproto.entity.Commande;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    List<Commande> getAllCommandes();
    Optional<Commande> getCommandeById(int idC);
    Commande saveCommande(Commande commande);
}
