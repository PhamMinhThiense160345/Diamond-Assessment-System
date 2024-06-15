package com.swp.DiamondAssesment.controller;

import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.DTO.assessmentRequestDTO;
import com.swp.DiamondAssesment.DTO.createPdfDTO;
import com.swp.DiamondAssesment.DTO.inspectParameterDTO;
import com.swp.DiamondAssesment.model.Assessment;
import com.swp.DiamondAssesment.service.assessmentRequestService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

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


    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> printPDF(@RequestParam int assessmentID) {
        try {

            createPdfDTO createPdfDTO = new createPdfDTO();
            createPdfDTO.getAssessmentID(assessmentID);

            ByteArrayInputStream bis = requestService.createPdf(createPdfDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=assessment.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}