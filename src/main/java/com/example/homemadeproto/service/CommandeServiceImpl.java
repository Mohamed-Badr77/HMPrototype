package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.CommandeRepository;
import com.example.homemadeproto.entity.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }
    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Optional<Commande> getCommandeById(int idC) {
        return commandeRepository.findById(idC);
    }

    @Override
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }
}
