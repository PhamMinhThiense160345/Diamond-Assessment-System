package com.swp.DiamondAssesment.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class assessmentRequestDTO {
    private Integer totalAmount;
    private Boolean status;
    private Integer user_id;
    private Integer service_id;
    private Integer payment_id;
    private Double sampleSize;
}
