package com.gch.back.repository;

import com.gch.back.entity.Prob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProbRepository extends JpaRepository<Prob, Integer>, ProbRepositoryCustom {
}