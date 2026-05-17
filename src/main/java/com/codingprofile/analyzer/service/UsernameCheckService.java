package com.codingprofile.analyzer.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsernameCheckService {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean checkLeetCode(String username) {

        String url = "https://leetcode.com/graphql";

        // 👉 ADD QUERY HERE
        String query = """
        query getUserProfile($username: String!) {
          matchedUser(username: $username) {
            username
            profile {
              ranking
            }
          }
        }
        """;

        // request body
        Map<String, Object> body = new HashMap<>();
        body.put("query", query);

        Map<String, String> variables = new HashMap<>();
        variables.put("username", username);

        body.put("variables", variables);

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            Map data = (Map) response.getBody().get("data");

            return data.get("matchedUser") != null;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkCodeforces(String username) {
        try {
            String url = "https://codeforces.com/api/user.info?handles=" + username;

            ResponseEntity<Map> response =
                    restTemplate.getForEntity(url, Map.class);

            Map body = response.getBody();

            return body != null && "OK".equals(body.get("status"));

        } catch (Exception e) {
            return false;
        }
    }
}