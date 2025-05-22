package com.example.todo_summary.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CohereResponse {
    private String response_id;
    private String text;
    private String generation_id;
}
