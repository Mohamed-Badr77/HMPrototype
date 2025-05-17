package com.example.homemadeproto.service;

import com.example.homemadeproto.DAO.ClientProfileRepository;
import com.example.homemadeproto.DAO.PanierRepository;
import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.entity.ClientProfile;
import com.example.homemadeproto.entity.Panier;
import com.example.homemadeproto.entity.Utilisateur;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UtilisateurRepository utilisateurRepository;
    private final ClientProfileRepository clientProfileRepository;
    private final PanierRepository panierRepository;

    @Autowired
    public AuthServiceImpl(UtilisateurRepository utilisateurRepository, ClientProfileRepository clientProfileRepository, PanierRepository panierRepository){
        this.clientProfileRepository = clientProfileRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.panierRepository = panierRepository;
    }

    public void registerClient(String nom, String prenom,String adresse, String email, String telephone, String password){
        Utilisateur user = new Utilisateur();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setPassword(password);
        user.addRole(Role.CLIENT);

        utilisateurRepository.save(user);


        ClientProfile client = new ClientProfile();
        client.setUtilisateur(user);
        client.setAdresseClient(adresse);

        Panier panier = new Panier();
        panier.setFraisLivraison(0);
        panier.setClient(client);
        panier.setClient(client);
        panierRepository.save(panier);

        client.setPanier(panier);

        clientProfileRepository.save(client);
    }
}
