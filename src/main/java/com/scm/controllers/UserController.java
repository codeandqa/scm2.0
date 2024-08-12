package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // dashboard
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // profile
    @RequestMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    // Add contact

    // view contacts

    // edit contact

    // view all contacts

    // search contacts
}
