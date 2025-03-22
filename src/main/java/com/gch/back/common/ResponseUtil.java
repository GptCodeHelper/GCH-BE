package com.gch.back.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gch.back.dto.common.CommonConstants;
import com.gch.back.dto.common.ResponseData;
import com.gch.back.dto.common.StatusConstants;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtil {
    @JsonSerialize
    public static class EmptyJsonResponse {}

    public static ResponseEntity<ResponseData> createSuccessData() {
        return createSuccessData(StatusConstants.SUCCESS, null, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData> createSuccessData(Object data) {
        return createSuccessData(StatusConstants.SUCCESS, data);
    }

    public static ResponseEntity<ResponseData> createSuccessData(String code, Object data) {
        return createSuccessData(code, data, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData> createSuccessData(String code, Object data, HttpStatus status) {
        return createSuccessResponse(code, data, status);
    }

    private static ResponseEntity<ResponseData> createSuccessResponse(String code, Object data, HttpStatus status) {
        if (data == null) {
            data = new EmptyJsonResponse();
        }

        return new ResponseEntity<>(
                ResponseData.builder()
                        .successYn(CommonConstants.YES_FLAG)
                        .code(code)
                        .data(data)
                        .build(),
                status);
    }

    public static ResponseEntity<ResponseData> createFailData() {
        return createFailData(HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData> createFailData(HttpStatus status) {
        return createFailData(StatusConstants.FAIL, status);
    }

    public static ResponseEntity<ResponseData> createFailData(String code) {
        return createFailData(code, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData> createFailData(String code, HttpStatus status) {
        return new ResponseEntity<>(createFailResponse(code), status);
    }

    private static ResponseData createFailResponse(String code) {
        return ResponseData.builder()
                .successYn(CommonConstants.NO_FLAG)
                .code(code)
                .build();
    }
}
