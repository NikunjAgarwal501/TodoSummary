package com.example.todo_summary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SlackService {

    @Value("${slack.webhook.url}")
    private String webhookUrl;
    private final WebClient webClient;

    public SlackService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public boolean sendSummaryToSlack(String message) {
        String payload = String.format("{\"text\": \"%s\"}", message.replace("\"", "\\\""));

        try {
            webClient.post()
                    .uri(webhookUrl)
                    .header("Content-Type", "application/json")
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
