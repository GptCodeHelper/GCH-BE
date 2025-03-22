package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import com.gch.back.dto.codeExecution.CodeExecutionRequest;
import com.gch.back.dto.common.ResponseData;
import com.gch.back.service.codeExecution.PythonExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/execution")
@RequiredArgsConstructor
public class CodeExecutionController {
    private final PythonExecutionService pythonExecutionService;

    @PostMapping("/python")
    public ResponseEntity<ResponseData> executePython(CodeExecutionRequest request) {
        boolean isCompiled = pythonExecutionService.compileCode(request.getCode());

        if (!isCompiled) {
            return ResponseUtil.createFailData("Compilation Failed");
        }

        Map<String, Boolean> testResults = pythonExecutionService.executeTestCases(request.getCode(), request.getTestCases());

        return ResponseUtil.createSuccessData(testResults);
    }
}
