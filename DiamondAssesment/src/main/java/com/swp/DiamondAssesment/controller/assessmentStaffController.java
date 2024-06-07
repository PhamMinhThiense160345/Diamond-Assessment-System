package com.swp.DiamondAssesment.controller;

import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.service.assessmentRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/assessment-staff")
public class assessmentStaffController {
    private final assessmentRequestService requestService;

    @PutMapping("/receive-delegation")
    ResponseEntity<ResponseObject> receiveDelegation(
            @RequestParam(name = "assessmentRequestDetailID", required = true) int assessmentRequestDetailID,
            @RequestParam(name = "user_id", required = true) int user_id
    ) {
        return requestService.receiveDelegation(assessmentRequestDetailID, user_id);
    }
}