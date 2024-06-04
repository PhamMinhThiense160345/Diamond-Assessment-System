package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assessment_requests_detail")
@Builder
public class AssessmentRequestsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int sampleSize;

    private boolean isDia;

    private boolean status;

    private boolean isCheckIn;

    private int price;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "byAssessmentID")
    private User byAssessmentID;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "ARequestID")
    private AssessmentRequests ARequestID; ;
}
