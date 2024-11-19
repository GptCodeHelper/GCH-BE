package com.gch.back.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface GptService {
    Mono<String> getChatResponse(String message);
}
