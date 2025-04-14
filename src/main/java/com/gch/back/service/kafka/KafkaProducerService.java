package com.gch.back.service.kafka;

import com.gch.back.dto.kafka.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${spring.kafka.topics.main}")
    private String mainTopic;
    
    @Value("${spring.kafka.topics.retry}")
    private String retryTopic;
    
    @Value("${spring.kafka.topics.dead}")
    private String deadLetterTopic;
    
    /**
     * 메인 토픽으로 메시지를 전송합니다.
     * 
     * @param messageType 메시지 유형
     * @param payload 메시지 내용
     * @return CompletableFuture<SendResult<String, Object>> 비동기 전송 결과
     */
    public CompletableFuture<SendResult<String, Object>> sendMessage(String messageType, Object payload) {
        KafkaMessageDto message = KafkaMessageDto.create(messageType, payload, "gch-service");
        return sendMessageToTopic(message, mainTopic);
    }
    
    /**
     * 재시도 토픽으로 메시지를 전송합니다.
     * 
     * @param message 재시도할 메시지
     * @return CompletableFuture<SendResult<String, Object>> 비동기 전송 결과
     */
    public CompletableFuture<SendResult<String, Object>> sendToRetryTopic(KafkaMessageDto message) {
        message.incrementRetryCount();
        return sendMessageToTopic(message, retryTopic);
    }
    
    /**
     * Dead Letter 토픽으로 메시지를 전송합니다.
     * 
     * @param message 실패한 메시지
     * @return CompletableFuture<SendResult<String, Object>> 비동기 전송 결과
     */
    public CompletableFuture<SendResult<String, Object>> sendToDeadLetterTopic(KafkaMessageDto message) {
        return sendMessageToTopic(message, deadLetterTopic);
    }
    
    /**
     * 지정된 토픽으로 메시지를 전송합니다.
     * 
     * @param message 전송할 메시지
     * @param topic 대상 토픽
     * @return CompletableFuture<SendResult<String, Object>> 비동기 전송 결과
     */
    private CompletableFuture<SendResult<String, Object>> sendMessageToTopic(KafkaMessageDto message, String topic) {
        log.info("Sending message to topic {}: {}", topic, message);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message.getId(), message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully to topic {}: {}", topic, message.getId());
                log.debug("Offset: {}", result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send message to topic {}: {}", topic, ex.getMessage(), ex);
            }
        });
        
        return future;
    }
}
