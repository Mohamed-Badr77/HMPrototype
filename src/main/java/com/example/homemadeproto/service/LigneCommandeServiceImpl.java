package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.LigneCommandeRepository;
import com.example.homemadeproto.entity.LigneCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeServiceImpl implements LigneCommandeService{
    private final LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    public List<LigneCommande> save(List<LigneCommande> ligneCommandes) {
        return ligneCommandeRepository.saveAll(ligneCommandes);
    }

    public List<LigneCommande> findByCommandeIdC(int idC) {
        return ligneCommandeRepository.findByCommandeIdC(idC);
    }

}
