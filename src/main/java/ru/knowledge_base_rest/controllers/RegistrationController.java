package ru.knowledge_base_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.knowledge_base_rest.entities.dto.RegistrationFormDTO;
import ru.knowledge_base_rest.entities.security.SecurityUser;
import ru.knowledge_base_rest.entities.user.AppUser;
import ru.knowledge_base_rest.services.security.SecurityUserDetailsService;

@Controller
public class RegistrationController {

    private final SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    public RegistrationController(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        RegistrationFormDTO registrationForm
                = new RegistrationFormDTO(new SecurityUser(), new AppUser());
        model.addAttribute("registrationForm", registrationForm);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(RegistrationFormDTO registrationForm,
                          Model model) {
        try {
            SecurityUser securityUser = registrationForm.getSecurityUser();
            AppUser squadUser = registrationForm.getSquadUser();
            securityUser.setAppUser(squadUser);
            squadUser.setSecurityUser(securityUser);
            securityUserDetailsService.addUser(securityUser);
            return "redirect:/";
        } catch (Exception ex) {
            model.addAttribute("registrationForm", registrationForm);
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}