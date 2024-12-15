package com.gch.back.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gch.back.dto.common.ExceptionDto;
import jakarta.annotation.Nullable;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;

public record ResponseUtil<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean success,
        @Nullable T data,
        @Nullable ExceptionDto error
) {
    public static <T> ResponseUtil<T> successResponse(@Nullable final T data) {
        return new ResponseUtil<>(HttpStatus.OK, true, data, null);
    }

    public static <T> ResponseUtil<T> failResponse(final CustomException e) {
        return new ResponseUtil<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()));
    }
}
