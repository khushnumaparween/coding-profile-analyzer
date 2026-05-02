package com.codingprofile.analyzer.service;

//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//
//@Service
//public class UsernameCheckService {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public boolean checkLeetCode(String username) {
//        try {
//            restTemplate.getForEntity("https://leetcode.com/" + username + "/", String.class);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean checkCodeforces(String username) {
//        try {
//            restTemplate.getForEntity("https://codeforces.com/profile/" + username, String.class);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
//

//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//
//@Service
//public class UsernameCheckService {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public boolean checkLeetCode(String username) {
//        try {
//            ResponseEntity<String> response =
//                    restTemplate.getForEntity("https://leetcode.com/" + username + "/", String.class);
//
//            String body = response.getBody();
//
//            // LeetCode returns 200 even for invalid users sometimes
//            return body != null && !body.contains("Page Not Found");
//
//        } catch (HttpClientErrorException.NotFound e) {
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean checkCodeforces(String username) {
//        try {
//            ResponseEntity<String> response =
//                    restTemplate.getForEntity("https://codeforces.com/profile/" + username, String.class);
//
//            String body = response.getBody();
//
//            // Codeforces invalid user detection
//            return body != null && !body.contains("handle not found");
//
//        } catch (HttpClientErrorException.NotFound e) {
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}

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