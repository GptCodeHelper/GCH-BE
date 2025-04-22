package com.gch.back.exception;

import lombok.Getter;

@Getter
public class KafkaProcessingException extends RuntimeException {
    
    private final String messageId;
    private final String messageType;
    
    public KafkaProcessingException(String messageId, String messageType, String message) {
        super(message);
        this.messageId = messageId;
        this.messageType = messageType;
    }
    
    public KafkaProcessingException(String messageId, String messageType, String message, Throwable cause) {
        super(message, cause);
        this.messageId = messageId;
        this.messageType = messageType;
    }
}
