package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entity.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

@Controller
public class HomePage {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String Home(Model model) {
        model.addAttribute("pageTitle", "home");
        model.addAttribute("name", "Smart Contact Manager");
        return "Home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About Us");
        return "about";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        model.addAttribute("pageTitle", "Services");
        return "services";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        model.addAttribute("pageTitle", "signup");
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "signup");
        return "login";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("processing started for the form data");
        // fetch form data.
        // validate form data
        System.out.println("Submit the form: " + userForm.toString());
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phoneNumber(userForm.getPhoneNumber())
                .about(userForm.getAbout())
                .build();
        User savedUser = userService.saveUser(user);
        System.out.println(savedUser + " ::::user saved in the db ");
        // save to the database
        // message = that successfull regration completed
        // redirect to signup page.
        return "redirect:signup";
    }
}
