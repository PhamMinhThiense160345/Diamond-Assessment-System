package com.swp.DiamondAssesment.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private String message;
    private String status;
    private Object payload;
}
