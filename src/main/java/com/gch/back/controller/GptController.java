package com.gch.back.controller;

import com.gch.back.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gpt")
public class GptController {
    private final GptService gptService;

    @PostMapping("/chat")
    public Mono<String> chat(@RequestBody String message) {
        return gptService.getChatResponse(message);
    }
}
