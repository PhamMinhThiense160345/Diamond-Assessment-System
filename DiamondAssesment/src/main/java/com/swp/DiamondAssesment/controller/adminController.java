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
@RequestMapping("api/v1/admin")
public class adminController {
    private final assessmentRequestService requestService;

    @PutMapping("/delegate")
    ResponseEntity<ResponseObject> delegateRequest(
            @RequestParam(name = "assessmentRequestDetailID", required = true) int assessmentRequestDetailID,
            @RequestParam(name = "user_id", required = true) int user_id
    ) {
        return requestService.delegateRequest(assessmentRequestDetailID, user_id);
    }
}
