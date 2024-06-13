package com.swp.DiamondAssesment.controller;

import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.DTO.assessmentRequestDTO;
import com.swp.DiamondAssesment.DTO.inspectParameterDTO;
import com.swp.DiamondAssesment.model.Assessment;
import com.swp.DiamondAssesment.service.assessmentRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/assessment-staff")
public class assessmentStaffController {
    private final assessmentRequestService requestService;
    private final com.swp.DiamondAssesment.serviceImpl.assessmentRequestServiceImpl assessmentRequestServiceImpl;

    @PutMapping("/receive-delegation")
    ResponseEntity<ResponseObject> receiveDelegation(
            @RequestParam(name = "assessmentRequestDetailID", required = true) int assessmentRequestDetailID,
            @RequestParam(name = "user_id", required = true) int user_id
    ) {
        return requestService.receiveDelegation(assessmentRequestDetailID, user_id);
    }

    @PostMapping("/inspect-parameter")
    public ResponseEntity<ResponseObject> inspectParameter(@RequestBody inspectParameterDTO inspectParameterDTO) {
        ResponseEntity<ResponseObject> saveAssessment = assessmentRequestServiceImpl.saveAssessment(inspectParameterDTO);
        return new ResponseEntity<>(new ResponseObject("success", "Assessment saved successfully", saveAssessment), HttpStatus.CREATED);
    }

}