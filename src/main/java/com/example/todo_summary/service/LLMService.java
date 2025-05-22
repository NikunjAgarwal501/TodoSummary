package com.example.todo_summary.service;

import com.example.todo_summary.payloads.CohereRequest;
import com.example.todo_summary.payloads.CohereResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LLMService {

    private final WebClient webClient;

    @Value("${cohere.api.key}")
    private String apiKey;

    @Autowired
    public LLMService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.cohere.ai").build();
    }

    public String summarizeTodos(List<String> todos) {
        String todosTogether = todos.stream().collect(Collectors.joining("\n- ", "- ", ""));
        String prompt = "Summarize these todos:\n" + todosTogether;

        CohereRequest request = new CohereRequest();
        request.setMessage(prompt);
        request.setModel("command-r-plus"); // Example updated model
        request.setTemperature(0.7);
        request.setMax_tokens(200);

        try {
            CohereResponse response = webClient.post()
                    .uri("/v1/chat")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Cohere-Version", "2022-12-06")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(CohereResponse.class)
                    .retryWhen(
                            Retry.backoff(3, Duration.ofSeconds(2))
                                    .jitter(0.5)
                                    .filter(throwable -> throwable instanceof WebClientResponseException.TooManyRequests)
                    )
                    .block();
            System.out.println(response);
            assert response != null;
            return response.getText().trim();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Failed to summarize todos: " + e.getResponseBodyAsString(), e);
        }
    }
}
