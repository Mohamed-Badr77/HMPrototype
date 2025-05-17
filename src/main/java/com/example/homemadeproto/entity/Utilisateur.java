package com.example.homemadeproto.entity;


import enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String password;
    private LocalDate dateCreation;


    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private ClientProfile clientProfile;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private CuisinierProfile cuisinierProfile;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private LivreurProfile livreurProfile;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @PrePersist
    public void onCreate(){
        this.dateCreation = LocalDate.now();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ClientProfile getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }

    public CuisinierProfile getCuisinierProfile() {
        return cuisinierProfile;
    }

    public void setCuisinierProfile(CuisinierProfile cuisinierProfile) {
        this.cuisinierProfile = cuisinierProfile;
    }

    public LivreurProfile getLivreurProfile() {
        return livreurProfile;
    }

    public void setLivreurProfile(LivreurProfile livreurProfile) {
        this.livreurProfile = livreurProfile;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

}
