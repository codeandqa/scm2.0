package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {

    @RequestMapping("/home")
    public String Home(Model model) {
        model.addAttribute("pageTitle", "home");
        model.addAttribute("name", "Smart Contact Manager");
        System.out.println("welcome to the home page");
        return "Home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        System.out.println("About page");
        model.addAttribute("pageTitle", "About Us");
        return "about";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        model.addAttribute("pageTitle", "Services");
        System.out.println("Services page");
        return "services";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("pageTitle", "signup");
        System.out.println("Signup page");
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "signup");
        System.out.println("Login page");
        return "login";
    }

}
