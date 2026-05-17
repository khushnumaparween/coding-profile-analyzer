package com.codingprofile.analyzer.controller;

import com.codingprofile.analyzer.dto.ProblemDTO;
import com.codingprofile.analyzer.service.CodeforcesService;
import com.codingprofile.analyzer.service.LeetCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import java.util.*;
        import java.util.stream.Collectors;

@Controller
public class ProfileController {

    private final CodeforcesService codeforcesService;
    private final LeetCodeService leetCodeService;

    public ProfileController(CodeforcesService codeforcesService,
                             LeetCodeService leetCodeService) {
        this.codeforcesService = codeforcesService;
        this.leetCodeService = leetCodeService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/analyze")
    public String analyze(@RequestParam String cfUsername,
                          @RequestParam(required = false) String lcUsername,
                          @RequestParam(required = false) String difficulty,
                          Model model) {

        List<ProblemDTO> problems = new ArrayList<>();

        //  Codeforces (required)
        List<ProblemDTO> cfProblems = codeforcesService.getSolvedProblems(cfUsername);
        problems.addAll(cfProblems);

        //  LeetCode (optional)
        if (lcUsername != null && !lcUsername.isEmpty()) {
            List<ProblemDTO> lcProblems = leetCodeService.getSolvedProblems(lcUsername);
            problems.addAll(lcProblems);
        }

        //Stats (calculate BEFORE filtering)
        long easy = problems.stream().filter(p -> p.getDifficulty().equals("Easy")).count();
        long medium = problems.stream().filter(p -> p.getDifficulty().equals("Medium")).count();
        long hard = problems.stream().filter(p -> p.getDifficulty().equals("Hard")).count();
        long unknown = problems.stream().filter(p -> p.getDifficulty().equals("Unknown")).count();

        long total = problems.size();

        //Apply filter (AFTER stats)
        if (difficulty != null && !difficulty.isEmpty()) {
            problems = problems.stream()
                    .filter(p -> p.getDifficulty().equalsIgnoreCase(difficulty))
                    .collect(Collectors.toList());
        }

        // Send to UI
        model.addAttribute("problems", problems);
        model.addAttribute("easyCount", easy);
        model.addAttribute("mediumCount", medium);
        model.addAttribute("hardCount", hard);
        model.addAttribute("unknownCount", unknown);
        model.addAttribute("totalCount", total);

        // Send usernames separately
        model.addAttribute("cfUsername", cfUsername);
        model.addAttribute("lcUsername", lcUsername);

        return "dashboard";
    }
}