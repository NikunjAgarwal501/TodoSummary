package com.example.todo_summary.controller;

import com.example.todo_summary.entity.Todo;
import com.example.todo_summary.payloads.ApiResponse;
import com.example.todo_summary.repository.TodoRepository;
import com.example.todo_summary.service.LLMService;
import com.example.todo_summary.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LLMController {

    @Autowired
    private LLMService llmService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private SlackService slackService;

    @PostMapping("/summarize")
    public ResponseEntity<ApiResponse> summarizeTodos(){
        List<String> todos = todoRepository.findAll()
                .stream().map(Todo::getTitle).toList();
        String summary =  llmService.summarizeTodos(todos);

        boolean success = slackService.sendSummaryToSlack(summary);
        ApiResponse response = new ApiResponse(success ? "Summary sent to Slack successfully!" : "Failed to send summary to Slack.",success);

        return ResponseEntity.status(success ? 200 : 500).body(response);
    }
}
