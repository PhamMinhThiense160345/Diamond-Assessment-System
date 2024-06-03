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
@RequestMapping("api/v1/consultant")
public class consultantController {
    private final assessmentRequestService requestService;

    @PutMapping("/checked")
    ResponseEntity<ResponseObject> checkedRequest(
            @RequestParam(name = "assessmentRequestDetailID", required = true) int assessmentRequestDetailID
    ) {
        return requestService.acceptRequest(assessmentRequestDetailID);
    }
}
