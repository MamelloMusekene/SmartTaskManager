package com.smarttaskmanager.controller;

import com.smarttaskmanager.dto.RegisterRequest;
import com.smarttaskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("registerRequest",
                new RegisterRequest());

        return "register";
    }

    @PostMapping("/register")
    public String register(

            @Valid
            @ModelAttribute RegisterRequest registerRequest,

            BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        userService.register(registerRequest);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}