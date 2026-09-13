package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        try {

            System.out.println("========== REGISTRATION ==========");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);

            User user = userService.registerUser(
                    username,
                    email,
                    password
            );

            System.out.println("USER SAVED SUCCESSFULLY");
            System.out.println("Generated ID: " + user.getId());
            System.out.println("Role: " + user.getRole());
            System.out.println("==================================");

            return "redirect:/login?registered=true";

        } catch (RuntimeException e) {

            System.out.println("========== REGISTRATION FAILED ==========");
            e.printStackTrace();

            model.addAttribute("error", e.getMessage());

            return "register";
        }
    }
}