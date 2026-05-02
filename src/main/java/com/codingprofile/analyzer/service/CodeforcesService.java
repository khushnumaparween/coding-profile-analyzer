//package com.codingprofile.analyzer.service;
//
//import com.codingprofile.analyzer.dto.ProblemDTO;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.*;
//
//@Service
//public class CodeforcesService {
//
//    private final WebClient webClient;
//
//    public CodeforcesService(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    public List<ProblemDTO> getSolvedProblems(String handle) {
//
//        String url = "https://codeforces.com/api/user.status?handle=" + handle;
//
//        Map response = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();
//
//        List<Map> result = (List<Map>) response.get("result");
//
//        Set<String> uniqueProblems = new HashSet<>();
//        List<ProblemDTO> problems = new ArrayList<>();
//
//        for (Map item : result) {
//
//            Map problem = (Map) item.get("problem");
//
//            String name = (String) problem.get("name");
//            Integer rating = (Integer) problem.get("rating");
//
//            if (name == null || rating == null) continue;
//
//            // Avoid duplicates
//            if (uniqueProblems.contains(name)) continue;
//            uniqueProblems.add(name);
//
//            String difficulty = mapDifficulty(rating);
//
//            problems.add(new ProblemDTO(
//                    name,
//                    difficulty,
//                    "Codeforces",
//                    "Unknown"
//            ));
//        }
//
//        return problems;
//    }
//
//    private String mapDifficulty(int rating) {
//        if (rating <= 1200) return "Easy";
//        if (rating <= 1800) return "Medium";
//        return "Hard";
//    }
//}
//

//this was working but contains duplicates questions

//package com.codingprofile.analyzer.service;
//
//import com.codingprofile.analyzer.dto.ProblemDTO;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.*;
//
//@Service
//public class CodeforcesService {
//
//    private final WebClient webClient;
//
//    public CodeforcesService(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    public List<ProblemDTO> getSolvedProblems(String handle) {
//
//        String url = "https://codeforces.com/api/user.status?handle=" + handle;
//
//        Map response = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(Map.class)
//                .block();
//
//        List<Map> result = (List<Map>) response.get("result");
//
//        List<ProblemDTO> problems = new ArrayList<>();
//
//        for (Map item : result) {
//
//            Map problem = (Map) item.get("problem");
//
//            if (problem == null) continue;
//
//            String name = (String) problem.get("name");
//            Integer rating = (Integer) problem.get("rating");
//
//            // Skip if no name
//            if (name == null) continue;
//
//            // Handle difficulty safely
//            String difficulty = "Unknown";
//            if (rating != null) {
//                difficulty = mapDifficulty(rating);
//            }
//
//            // ✅ Build problem URL
//            Object contestIdObj = problem.get("contestId");
//            String index = (String) problem.get("index");
//
//            String problemUrl = "";
//
//            if (contestIdObj != null && index != null) {
//                String contestId = String.valueOf(contestIdObj);
//
//                problemUrl = "https://codeforces.com/problemset/problem/"
//                        + contestId + "/" + index;
//            }
//
//            problems.add(new ProblemDTO(
//                    name,
//                    difficulty,
//                    "Codeforces",
//                    "Unknown",
//                    problemUrl
//            ));
//        }
//
//        return problems;
//    }
//
//    private String mapDifficulty(int rating) {
//        if (rating <= 1200) return "Easy";
//        if (rating <= 1800) return "Medium";
//        return "Hard";
//    }
//}


//removing duplicates
package com.codingprofile.analyzer.service;

import com.codingprofile.analyzer.dto.ProblemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CodeforcesService {

    private final WebClient webClient;

    public CodeforcesService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ProblemDTO> getSolvedProblems(String handle) {

        //  Limit results to avoid huge response
        String url = "https://codeforces.com/api/user.status?handle="
                + handle + "&count=200";

        Map response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map> result = (List<Map>) response.get("result");

        List<ProblemDTO> problems = new ArrayList<>();
        Set<String> seenProblems = new HashSet<>();

        for (Map item : result) {

            String verdict = (String) item.get("verdict");

            //  Only accepted submissions
            if (!"OK".equals(verdict)) continue;

            Map problem = (Map) item.get("problem");
            if (problem == null) continue;

            String name = (String) problem.get("name");
            Integer rating = (Integer) problem.get("rating");

            Object contestIdObj = problem.get("contestId");
            String index = (String) problem.get("index");

            if (name == null || contestIdObj == null || index == null) continue;

            //  Unique key to remove duplicates
            String uniqueKey = contestIdObj + "-" + index;

            if (seenProblems.contains(uniqueKey)) continue;
            seenProblems.add(uniqueKey);

            //  Difficulty mapping
            String difficulty = "Unknown";
            if (rating != null) {
                difficulty = mapDifficulty(rating);
            }

            //  Build problem URL
            String problemUrl = "https://codeforces.com/problemset/problem/"
                    + contestIdObj + "/" + index;

            problems.add(new ProblemDTO(
                    name,
                    difficulty,
                    "Codeforces",
                    "Unknown",
                    problemUrl
            ));
        }

        return problems;
    }

    private String mapDifficulty(int rating) {
        if (rating <= 1200) return "Easy";
        if (rating <= 1800) return "Medium";
        return "Hard";
    }
}