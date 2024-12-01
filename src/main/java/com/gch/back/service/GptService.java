package com.gch.back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gch.back.model.gpt.MessageVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface GptService {
    Mono<String> getChatResponse(MessageVO message) throws JsonProcessingException;
}
