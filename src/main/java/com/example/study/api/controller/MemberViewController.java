package com.example.study.api.controller;

import com.example.study.api.dto.request.MemberRequestDTO;
import com.example.study.api.service.command.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.JoinDto request,
                             BindingResult bindingResult,
                             Model model) {
        System.out.println("Received Signup Request: " + request);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "signup";
        }

        try {
            memberCommandService.joinMember(request);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Error during signup: " + e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
