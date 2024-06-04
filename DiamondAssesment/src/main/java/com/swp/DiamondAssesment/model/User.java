package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tblUsers")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @Nationalized
    private String name;

    @Column(length = 50)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    @Nationalized
    private String address;

    @Column(length = 50)
    private String CCCD;


    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonManagedReference
    private Role role_id;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Payment> payments  = new ArrayList<>();

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AssessmentRequestsDetail> assessmentRequestsDetails = new ArrayList<>();

    @OneToMany(mappedBy = "byAssessmentID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonBackReference
    private List<AssessmentRequestsDetail> assessmentRequestsDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AssessmentRequests> assessmentRequests = new ArrayList<>();
}
