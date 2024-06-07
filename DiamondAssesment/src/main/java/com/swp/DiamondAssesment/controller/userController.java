package com.swp.DiamondAssesment.controller;

import com.swp.DiamondAssesment.DTO.ResponseObject;
import com.swp.DiamondAssesment.DTO.assessmentRequestDTO;
import com.swp.DiamondAssesment.DTO.searchRequestDTO;
import com.swp.DiamondAssesment.service.assessmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class userController {
    private final assessmentRequestService requestService;

    @PostMapping("/create")
    ResponseEntity<ResponseObject> createRequest(
            @RequestBody assessmentRequestDTO request) {
        return requestService.createRequest(request);
    }

    @PostMapping("/search")
    ResponseEntity<ResponseObject> searchRequest(@RequestBody searchRequestDTO searchDTO) {
        return requestService.searchRequest(searchDTO);
    }

}
