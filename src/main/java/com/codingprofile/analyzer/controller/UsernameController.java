package com.codingprofile.analyzer.controller;

import com.codingprofile.analyzer.service.UsernameCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsernameController {

    private final UsernameCheckService service;

    public UsernameController(UsernameCheckService service) {
        this.service = service;
    }

    // UsernameController
    @GetMapping("/username")
    public String home() {
        return "username";
    }
//
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

    @PostMapping("/check")
    public String checkUsername(@RequestParam String username, Model model) {

        boolean lcExists = service.checkLeetCode(username);
        boolean cfExists = service.checkCodeforces(username);

        model.addAttribute("username", username);
        model.addAttribute("leetcode", lcExists);
        model.addAttribute("codeforces", cfExists);

        return "result";
    }
}
