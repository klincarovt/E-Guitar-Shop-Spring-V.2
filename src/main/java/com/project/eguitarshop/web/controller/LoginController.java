package com.project.eguitarshop.web.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private List<String> allowedNames;

    @PostConstruct
    public void init() {
        this.allowedNames = new ArrayList<>();
        this.allowedNames.add("admin");
    }

    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String info,
                               Model model){
        model.addAttribute("info",info);
        return "login";
    }
}
