package com.gch.back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gch.back.model.gpt.GptRequestVO;
import com.gch.back.model.gpt.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {
    @Value("${openai.api_key}")
    String apiKey;

    @Value("${openai.base_url}")
    String baseUrl;

    WebClient webClient = WebClient.create();

    @Override
    public Mono<String> getChatResponse(MessageVO message) throws JsonProcessingException {
        GptRequestVO request = GptRequestVO.builder()
                .model("gpt-3.5-turbo")
                .message(message)
                .max_tokens(100)
                .build();

        // VO를 JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        return webClient.post()
                .uri(baseUrl + "/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestJson)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.just("Error: " + e.getMessage()));
    }

}
