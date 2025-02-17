package com.gch.back.repository;

import com.gch.back.entity.Prob;

import java.util.Optional;

public interface ProbRepositoryCustom {
    Optional<Prob> findProblemWithDetails(Integer probId) throws Exception;
}
