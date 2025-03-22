package com.gch.back.dto.codeExecution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CodeExecutionRequest {
    private String code;
    private Map<String, String> testCases;
}
