package com.gch.back.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {
    String apiKey = System.getenv("OPENAI_API_KEY");

    @Value("${openai.base-url}")
    String baseUrl;

    WebClient webClient = WebClient.create();

    @Override
    public Mono<String> getChatResponse(String message) {
        return webClient.post()
                .uri(baseUrl + "/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(new ChatRequest(message))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.just("Error: " + e.getMessage()));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ChatRequest {
        private String model = "gpt-3.5-turbo"; // 사용할 모델
        private String prompt;

        public ChatRequest(String prompt) {
            this.prompt = prompt;
        }
    }

}
