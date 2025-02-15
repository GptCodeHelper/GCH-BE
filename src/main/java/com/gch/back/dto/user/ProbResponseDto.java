package com.gch.back.dto.user;

import com.gch.back.entity.Lan;
import com.gch.back.entity.Prob;
import com.gch.back.entity.ProbCon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProbResponseDto {
    private Integer probId;
    private String probTit;
    private LocalDateTime regTime;
    private String regId;
    private LocalDateTime chgDt;
    private String chgId;
    private List<LanResponse> lanProbs;
    private List<ProbConResponse> probCons;

    public static ProbResponseDto from(Prob entity) {
        return ProbResponseDto.builder()
                .probId(entity.getProbId())
                .probTit(entity.getProbTit())
                .regTime(entity.getRegTime())
                .regId(entity.getRegId())
                .chgDt(entity.getChgDt())
                .chgId(entity.getChgId())
                .lanProbs(entity.getLanProbs().stream()
                        .map(LanResponse::from)
                        .collect(Collectors.toList()))
                .probCons(entity.getProbCons().stream()
                        .map(ProbConResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LanResponse {
        private Integer lanId;
        private String lanNM;
        private LocalDateTime regTime;
        private String regId;
        private LocalDateTime chgDt;
        private String chgId;

        public static LanResponse from(Lan entity) {
            return LanResponse.builder()
                    .lanId(entity.getLanId())
                    .lanNM(entity.getLanNM())
                    .regTime(entity.getRegTime())
                    .regId(entity.getRegId())
                    .chgDt(entity.getChgDt())
                    .chgId(entity.getChgId())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProbConResponse {
        private Integer probConId;
        private String probConSort;
        private String probCont;
        private LocalDateTime regTime;
        private String regId;
        private LocalDateTime chgDt;
        private String chgId;

        public static ProbConResponse from(ProbCon entity) {
            return ProbConResponse.builder()
                    .probConId(entity.getProbConId())
                    .probConSort(entity.getProbConSort())
                    .probCont(entity.getProbCont())
                    .regTime(entity.getRegTime())
                    .regId(entity.getRegId())
                    .chgDt(entity.getChgDt())
                    .chgId(entity.getChgId())
                    .build();
        }
    }
}
