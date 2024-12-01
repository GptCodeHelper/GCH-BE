package com.gch.back.model.gpt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GptRequestVO {
    // Default use model : gpt-3.5-turbo
    private String model;
    private MessageVO message;
    private int max_tokens;
}
