package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assessment_requests")
@Builder
public class AssessmentRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int totalAmount;
    private boolean status;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "service_id")
    private Service service_id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "payment_id")
    private Payment payment_id;

    @OneToMany(mappedBy = "ARequestID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AssessmentRequestsDetail> assessmentRequestsDetails = new ArrayList<>();
}
