package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import com.gch.back.dto.sub.SaveSubConRequestDto;
import com.gch.back.dto.sub.SubRequestDto;
import com.gch.back.dto.common.ResponseData;
import com.gch.back.entity.SubCon;
import com.gch.back.service.python.InterpreterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/python")
@RequiredArgsConstructor
public class PythonInterpreterController {
    private final InterpreterService pythonService;

    @PostMapping("/regist/detail")
    // request의 데이터를 제출 내용 db에 저장하는 로직
    // request.getSubConCOde() : 제출 코드
    // request.getTestCases() : 테스트 케이스
    public ResponseEntity<ResponseData> saveDetail(SaveSubConRequestDto request) {
        SubCon subCon = pythonService.saveDetail(request);

        return ResponseUtil.createSuccessData(subCon);
    }


}
