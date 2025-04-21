package com.gch.back.controller;

import com.gch.back.dto.common.ResponseData;
import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    /**
     * 사용자 생성 이벤트를 카프카로 발행합니다.
     *
     * @param userRequestDto 사용자 정보
     * @return ResponseEntity 응답
     */
    @PostMapping("/user/created")
    public ResponseEntity<ResponseData> publishUserCreatedEvent(@RequestBody UserRequestDto userRequestDto) {
        log.info("Publishing user created event: {}", userRequestDto);
        try {
            kafkaProducerService.sendMessage("USER_CREATED", userRequestDto);
            return ResponseEntity.ok(ResponseData.builder()
                    .successYn("Y")
                    .code("success")
                    .data("Message sent successfully")
                    .build());
        } catch (Exception e) {
            log.error("Error publishing user created event", e);
            return ResponseEntity.internalServerError()
                    .body(ResponseData.builder()
                            .successYn("N")
                            .code("error")
                            .data("Failed to send message: " + e.getMessage())
                            .build());
        }
    }

    /**
     * 사용자 업데이트 이벤트를 카프카로 발행합니다.
     *
     * @param userRequestDto 사용자 정보
     * @return ResponseEntity 응답
     */
    @PostMapping("/user/updated")
    public ResponseEntity<ResponseData> publishUserUpdatedEvent(@RequestBody UserRequestDto userRequestDto) {
        log.info("Publishing user updated event: {}", userRequestDto);
        try {
            kafkaProducerService.sendMessage("USER_UPDATED", userRequestDto);
            return ResponseEntity.ok(ResponseData.builder()
                    .successYn("Y")
                    .code("success")
                    .data("Message sent successfully")
                    .build());
        } catch (Exception e) {
            log.error("Error publishing user updated event", e);
            return ResponseEntity.internalServerError()
                    .body(ResponseData.builder()
                            .successYn("N")
                            .code("error")
                            .data("Failed to send message: " + e.getMessage())
                            .build());
        }
    }
}
