package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assessment_requests")
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date TSDate;

    @Column(length = 100)
    @Nationalized
    private String serviceType;

    private int totalAmount;
    private boolean status;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user_id;
}
