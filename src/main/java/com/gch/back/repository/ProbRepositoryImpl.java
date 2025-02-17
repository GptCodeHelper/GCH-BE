package com.gch.back.repository;

import com.gch.back.entity.Prob;
import com.gch.back.entity.QLan;
import com.gch.back.entity.QProb;
import com.gch.back.entity.QProbCon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProbRepositoryImpl implements ProbRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Prob> findProblemWithDetails(Integer probId) throws Exception {
        QProb prob = QProb.prob;
        QProbCon probCon = QProbCon.probCon;
        QLan lan = QLan.lan;

        return Optional.ofNullable(queryFactory
                .selectFrom(prob)
                .leftJoin(prob.probCons, probCon).fetchJoin()
                .leftJoin(prob.lanProbs, lan).fetchJoin()
                .where(prob.probId.eq(probId))
                .fetchOne());
    }
}
