package com.project.eguitarshop.web.controller;

import com.project.eguitarshop.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final AuthenticationService authenticationService;

    public SignUpController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUpUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String repeatedPassword) {
        try {
            this.authenticationService.signUpUser(username,password,repeatedPassword);
            return "redirect:/login?info=SuccessfulRegistration!";
        } catch (RuntimeException ex) {
            return "redirect:/signup?error=" + ex.getLocalizedMessage();
        }
    }
}
