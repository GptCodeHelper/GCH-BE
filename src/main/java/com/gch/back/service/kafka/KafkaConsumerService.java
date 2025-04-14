package com.gch.back.service.kafka;

import com.gch.back.dto.kafka.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaProducerService kafkaProducerService;
    
    // 메시지 처리 최대 재시도 횟수
    private static final int MAX_RETRIES = 3;

    /**
     * 메인 토픽의 메시지를 처리합니다.
     * 
     * @param message 메시지 페이로드
     * @param key 메시지 키
     * @param partition 파티션 번호
     * @param topic 토픽 이름
     * @param offset 오프셋 위치
     */
    @KafkaListener(topics = "${spring.kafka.topics.main}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMainTopic(
            @Payload KafkaMessageDto message,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset) {
        
        log.info("Received message from {}-{} @ offset {}: {}", topic, partition, offset, message);
        
        try {
            // 메시지 유형에 따라 처리 로직 분기
            processMessage(message);
            log.info("Successfully processed message: {}", key);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
            handleProcessingError(message, e);
        }
    }
    
    /**
     * 재시도 토픽의 메시지를 처리합니다.
     * 
     * @param message 재시도 메시지
     */
    @KafkaListener(topics = "${spring.kafka.topics.retry}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRetryTopic(@Payload KafkaMessageDto message) {
        log.info("Retry processing message: {}", message);
        
        try {
            // 재시도 로직 - 원본과 동일한 처리 로직 적용
            processMessage(message);
            log.info("Successfully processed retry message: {}", message.getId());
        } catch (Exception e) {
            log.error("Error processing retry message: {}", e.getMessage(), e);
            handleProcessingError(message, e);
        }
    }
    
    /**
     * Dead Letter 토픽의 메시지를 기록합니다.
     * Dead Letter 토픽은 주로 모니터링 및 수동 조치를 위해 사용됩니다.
     * 
     * @param message 실패한 메시지
     */
    @KafkaListener(topics = "${spring.kafka.topics.dead}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeDeadLetterTopic(@Payload KafkaMessageDto message) {
        log.error("Message in dead letter queue: {}", message);
        // 여기서는 로깅만 수행하지만, 필요에 따라 알림 발송 또는 DB 저장 등의 로직 추가 가능
    }
    
    /**
     * 메시지 유형에 따라 적절한 처리 로직을 수행합니다.
     * 
     * @param message 처리할 메시지
     */
    private void processMessage(KafkaMessageDto message) {
        switch (message.getMessageType()) {
            case "USER_CREATED":
                processUserCreated(message);
                break;
            case "USER_UPDATED":
                processUserUpdated(message);
                break;
            // 추가 메시지 유형에 따른 처리 로직
            default:
                log.warn("Unknown message type: {}", message.getMessageType());
                break;
        }
    }
    
    /**
     * 사용자 생성 이벤트를 처리합니다.
     * 
     * @param message 사용자 생성 메시지
     */
    private void processUserCreated(KafkaMessageDto message) {
        log.info("Processing USER_CREATED event: {}", message);
        // 사용자 생성에 따른 비즈니스 로직 구현
    }
    
    /**
     * 사용자 업데이트 이벤트를 처리합니다.
     * 
     * @param message 사용자 업데이트 메시지
     */
    private void processUserUpdated(KafkaMessageDto message) {
        log.info("Processing USER_UPDATED event: {}", message);
        // 사용자 업데이트에 따른 비즈니스 로직 구현
    }
    
    /**
     * 메시지 처리 오류를 처리합니다.
     * 재시도 횟수에 따라 재시도 또는 Dead Letter 토픽으로 전송합니다.
     * 
     * @param message 실패한 메시지
     * @param exception 발생한 예외
     */
    private void handleProcessingError(KafkaMessageDto message, Exception exception) {
        if (message.getRetryCount() < MAX_RETRIES) {
            log.info("Sending message to retry topic. Retry count: {}", message.getRetryCount());
            kafkaProducerService.sendToRetryTopic(message);
        } else {
            log.error("Max retries exceeded. Sending message to dead letter topic: {}", message);
            kafkaProducerService.sendToDeadLetterTopic(message);
        }
    }
}
