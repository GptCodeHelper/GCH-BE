package com.gch.back.service;

import com.gch.back.dto.prob.ProbResponseDto;
import com.gch.back.entity.Prob;
import com.gch.back.repository.ProbRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProbService {
    private final ProbRepository probRepository;

    public ProbResponseDto getProblem(Integer probId) throws Exception {
        Prob prob = probRepository.findProblemWithDetails(probId)
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with id: " + probId));
        return ProbResponseDto.from(prob);
    }
}
