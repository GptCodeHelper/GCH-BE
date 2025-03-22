package com.gch.back.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData {
    private String successYn;
    private String code;
    private Object data;
}
