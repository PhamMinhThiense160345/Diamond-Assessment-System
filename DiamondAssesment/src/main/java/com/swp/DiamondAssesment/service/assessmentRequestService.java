package com.swp.DiamondAssesment.service;

import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.DTO.assessmentRequestDTO;
import org.springframework.http.ResponseEntity;

public interface assessmentRequestService {
    ResponseEntity<ResponseObject> createRequest(assessmentRequestDTO assessmentRequestDTO);

    ResponseEntity<ResponseObject> acceptRequest(int assessmentRequestDetailID);

    ResponseEntity<ResponseObject> delegateRequest(int assessmentRequestDetailID, int user_id);
}
