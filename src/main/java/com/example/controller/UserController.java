package com.example.controller;

import com.example.model.People;
import com.example.model.User;
import com.example.repository.PeopleRepository;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/")
    public String index() {
        return "dashboard";
    }

    @GetMapping("/index")
    public String dashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if session is invalid
        }
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/index"; // Redirect to dashboard if the user is already logged in
        }
        return "login"; // Show the login page
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user); // Save user info in session
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @PostMapping("/contact")
    public String addPeopleResponsive(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        People pe = new People();
        pe.setName(name);
        pe.setEmail(email);
        pe.setMessage(message);
        peopleRepository.saveRes(pe);
        return "redirect:/contact";
    }

    @GetMapping("/portfolio")
    public String showPortfolioPage() {
        
        return "portfolio";
    }

    // Logout Method
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/login"; // Redirect to login page
    }
}
