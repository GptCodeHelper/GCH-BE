package com.gch.back.dto.sub;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SubConRequestDto {
    private String subConCode;
    private String subConSort;
}
