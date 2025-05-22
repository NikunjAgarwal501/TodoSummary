package com.example.todo_summary.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CohereRequest {
    private String model;
    private String message;
    private Double temperature;
    private Integer max_tokens;
}
