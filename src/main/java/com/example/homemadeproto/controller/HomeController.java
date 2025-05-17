package com.example.homemadeproto.controller;


import com.example.homemadeproto.DAO.CuisinierProfileRepository;
import com.example.homemadeproto.DAO.UtilisateurRepository;
import com.example.homemadeproto.DTO.CookApplication;
import com.example.homemadeproto.entity.CuisinierProfile;
import com.example.homemadeproto.entity.Utilisateur;
import com.example.homemadeproto.service.PlatService;
import enums.Role;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class HomeController {

    private CuisinierProfileRepository cuisinierProfileRepository;
    private UtilisateurRepository utilisateurRepository;
    private PlatService platService;

    @Autowired
    public HomeController(CuisinierProfileRepository cuisinierProfileRepository, UtilisateurRepository utilisateurRepository, PlatService platService) {
        this.cuisinierProfileRepository = cuisinierProfileRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.platService = platService;
    }
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        model.addAttribute("user", utilisateur);
        return "home";
    }

    @GetMapping("/CookApplication")
    public String cookApplication(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        model.addAttribute("cookApp", new CookApplication());
        if(utilisateur == null) {
            return "redirect:/signin";
        }

        if(utilisateur.getCuisinierProfile() != null) {
            return "redirect:/dishes";
        }

        model.addAttribute("user", utilisateur);
        return "cook-application";
    }

    @PostMapping("/CookApplication")
    public String submitCookApplication(HttpSession session, @Valid @ModelAttribute("cookApp") CookApplication cookApp, BindingResult bindingResult, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if(utilisateur == null) {
            return "redirect:/signin";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", utilisateur);
            return "cook-application";
        }

        CuisinierProfile cuisinierProfile = new CuisinierProfile();
        cuisinierProfile.setUtilisateur(utilisateur);
        cuisinierProfile.setAdresseCuisinier(cookApp.getAdresse());
        cuisinierProfile.setSpecialiteCulinaire(cookApp.getSpecialite());
        cuisinierProfile.setEstVerifie(true);

        utilisateur.setCuisinierProfile(cuisinierProfile);
        utilisateur.addRole(Role.CUISINIER);
        utilisateurRepository.save(utilisateur);

        return "redirect:/";
    }


    @GetMapping("/profile")
    public String profileLink(HttpSession session,Model model){
        Utilisateur sessionuser = (Utilisateur) session.getAttribute("user");

        if(sessionuser == null) {
            return "redirect:/signin";
        }
        Utilisateur user = utilisateurRepository.findById(sessionuser.getUserId()).orElse(null);
        if(user == null) {
            return "redirect:/signin";
        }
        model.addAttribute("user", user);
        if(user.hasRole(Role.CUISINIER)) {
            model.addAttribute("cuisinierProfile", user.getCuisinierProfile());
            model.addAttribute("plats", platService.findDishesByCuisinier(user.getCuisinierProfile()));
            model.addAttribute("clientProfile", user.getClientProfile());
            model.addAttribute("commandes",user.getClientProfile().getCommandes());
        }
        if (user.hasRole(Role.CLIENT)) {
            model.addAttribute("clientProfile", user.getClientProfile());
            model.addAttribute("commandes",user.getClientProfile().getCommandes());
        }

        return "profile";
    }


}
