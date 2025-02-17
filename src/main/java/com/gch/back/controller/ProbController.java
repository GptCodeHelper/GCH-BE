package com.gch.back.controller;

import com.gch.back.dto.prob.ProbResponseDto;
import com.gch.back.service.ProbService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProbController {
    Logger logger = LoggerFactory.getLogger(ProbController.class);
    private final ProbService probService;

    @GetMapping("/{probId}")
    public ResponseEntity<ProbResponseDto> getProblemToProbId(@PathVariable Integer probId) {
        try {
            ProbResponseDto response = probService.getProblem(probId);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            logger.error("getProblem Problem not found", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("getProblem Exception", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
