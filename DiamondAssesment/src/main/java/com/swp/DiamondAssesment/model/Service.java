package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Service")
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 100)
    @Nationalized
    private String serviceName;

    @OneToMany(mappedBy = "service_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AssessmentRequests> assessmentRequestsList = new ArrayList<>();
}
