package com.swp.DiamondAssesment.service;

import com.swp.DiamondAssesment.DTO.*;
import com.swp.DiamondAssesment.model.Assessment;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public interface assessmentRequestService {



    ResponseEntity<ResponseObject> createRequest(assessmentRequestDTO assessmentRequestDTO);

    ResponseEntity<ResponseObject> acceptRequest(int assessmentRequestDetailID);

    ResponseEntity<ResponseObject> delegateRequest(int assessmentRequestDetailID, int user_id);


    ResponseEntity<ResponseObject> receiveDelegation(int assessmentRequestDetailID, int managerID);

    ResponseEntity<ResponseObject> searchRequest(searchRequestDTO searchDTO);

    ResponseEntity<ResponseObject> saveAssessment(inspectParameterDTO inspectParameterDTO);

    ByteArrayInputStream createPdf(createPdfDTO createPdfDTO);

}
