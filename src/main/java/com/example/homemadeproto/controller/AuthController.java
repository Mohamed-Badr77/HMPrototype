package com.example.homemadeproto.controller;


import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.DTO.LoginRequest;
import com.example.homemadeproto.DTO.SignupRequest;
import com.example.homemadeproto.entity.Utilisateur;
import com.example.homemadeproto.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {
    private final AuthService authService;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public AuthController(AuthService authService, UtilisateurRepository utilisateurRepository) {
        this.authService = authService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }


    @PostMapping("/signup")
    public String processSignup(@ModelAttribute SignupRequest signupRequest, Model model){
        authService.registerClient(
                signupRequest.getNom(),
                signupRequest.getPrenom(),
                signupRequest.getAdresse(),
                signupRequest.getEmail(),
                signupRequest.getTelephone(),
                signupRequest.getPassword()
        );
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "signin";
    }

    @PostMapping("/signin")
    public String processSignin(
            @ModelAttribute LoginRequest loginRequest,
            HttpSession session,
            Model model
    ) {
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (optionalUser.isPresent()) {
            Utilisateur user = optionalUser.get();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "signin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
