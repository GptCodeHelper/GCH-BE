package com.gch.back.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessageDto implements Serializable {
    private String id;
    private String messageType;
    private Object payload;
    private LocalDateTime timestamp;
    private String source;
    
    // 추가 메타데이터
    private int retryCount;
    
    public static KafkaMessageDto create(String messageType, Object payload, String source) {
        return KafkaMessageDto.builder()
                .id(java.util.UUID.randomUUID().toString())
                .messageType(messageType)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .source(source)
                .retryCount(0)
                .build();
    }
    
    public KafkaMessageDto incrementRetryCount() {
        this.retryCount++;
        return this;
    }
}
