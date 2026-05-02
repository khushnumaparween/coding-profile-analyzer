package com.codingprofile.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {

    private String title;
    private String difficulty; // Easy, Medium, Hard
    private String platform;   // LeetCode / Codeforces
    private String topic;      // Array, Graph, etc.
    private String url;
}
