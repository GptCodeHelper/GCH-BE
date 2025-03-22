package com.gch.back.service.codeExecution;

import com.gch.back.dto.common.CommonConstants;
import com.gch.back.repository.codeExecution.PythonExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PythonExecutionService {
    private final PythonExecutionRepository pythonExecuteRepository;

    public boolean compileCode(String code) {
        try {
            Path tempFile = Files.createTempFile("user_code_", ".py");
            Files.write(tempFile, code.getBytes());

            ProcessBuilder pb = new ProcessBuilder(CommonConstants.PYTHON_PATH, "-m", "py_compile", tempFile.toString());
            Process process = pb.start();

            int exitCode = process.waitFor();
            Files.deleteIfExists(tempFile);

            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Boolean> executeTestCases(String code, Map<String, String> testCases) {
        Map<String, Boolean> results = new HashMap<>();

        try {
            Path tempFile = Files.createTempFile("user_code_run_", ".py");
            Files.write(tempFile, code.getBytes());

            for (Map.Entry<String, String> testCase : testCases.entrySet()) {
                String input = testCase.getKey();
                String expectedOutput = testCase.getValue();

                ProcessBuilder pb = new ProcessBuilder(CommonConstants.PYTHON_PATH, tempFile.toString());
                Process process = pb.start();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                writer.write(input);
                writer.flush();
                writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = reader.lines().collect(Collectors.joining("\n")).trim();

                int exitCode = process.waitFor();
                results.put(input, exitCode == 0 && output.equals(expectedOutput));
            }

            Files.deleteIfExists(tempFile);
        } catch (Exception e) {
            testCases.keySet().forEach(tc -> results.put(tc, false));
        }

        return results;
    }
}
