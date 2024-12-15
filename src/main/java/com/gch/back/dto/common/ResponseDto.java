package com.gch.back.dto.common;

import com.gch.back.dto.constant.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private Yn successYn;
    private String body;
    private String error;
}
