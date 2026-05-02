package com.codingprofile.analyzer.service;

import com.codingprofile.analyzer.dto.ProblemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class LeetCodeService {

    private final WebClient webClient;

    public LeetCodeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ProblemDTO> getSolvedProblems(String username) {

        String url = "https://leetcode.com/graphql";

        // GraphQL Query
        String query = """
        query getUserProfile($username: String!) {
          matchedUser(username: $username) {
            submitStats {
              acSubmissionNum {
                difficulty
                count
              }
            }
          }
        }
        """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", query);
        requestBody.put("variables", variables);

        Map response = webClient.post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<ProblemDTO> problems = new ArrayList<>();

        try {
            Map data = (Map) response.get("data");
            Map matchedUser = (Map) data.get("matchedUser");
            Map submitStats = (Map) matchedUser.get("submitStats");

            List<Map> stats = (List<Map>) submitStats.get("acSubmissionNum");

            for (Map item : stats) {

                String difficulty = (String) item.get("difficulty");
                Integer count = (Integer) item.get("count");

                // We don't get problem names here → so create placeholders
                for (int i = 0; i < count; i++) {
                    problems.add(new ProblemDTO(
                            "LeetCode Problem",
                            difficulty,
                            "LeetCode",
                            "Unknown",
                            "https://leetcode.com/problemset/"
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("LeetCode parsing error");
        }

        return problems;
    }
}
