package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assessment")
@Builder

public class Assessment {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "isDia")
        private boolean isDia;

        @OneToOne
        @JoinColumn(name = "DetailID")
        private AssessmentRequestsDetail DetailID;

        @Column(name = "Comments", length = 100)
        private String comments;

        @Column(name = "Depth", precision = 18, scale = 3)
        private BigDecimal depth;

        @Column(name = "Tablee", precision = 18, scale = 3)
        private BigDecimal tablee;

        @Column(name = "CrowAngle", precision = 18, scale = 3)
        private BigDecimal crowAngle;

        @Column(name = "CrowHeight", precision = 18, scale = 3)
        private BigDecimal crowHeight;

        @Column(name = "PavillionAngle", precision = 18, scale = 3)
        private BigDecimal pavillionAngle;

        @Column(name = "PavillionDepth", precision = 18, scale = 3)
        private BigDecimal pavillionDepth;

        @Column(name = "StarLength", precision = 18, scale = 3)
        private BigDecimal starLength;

        @Column(name = "LowerHalf", precision = 18, scale = 3)
        private BigDecimal lowerHalf;

        @Column(name = "Girdle", length = 100)
        private String girdle;

        @Column(name = "CaratWeight", precision = 18, scale = 3)
        private BigDecimal caratWeight;

        @Column(name = "ColorGrade", length = 100)
        private String colorGrade;

        @Column(name = "ClarityGrade", length = 100)
        private String clarityGrade;

        @Column(name = "CutGrade", length = 100)
        private String cutGrade;

        @Column(name = "Proportions", length = 100)
        private String proportions;

        @Column(name = "Polish", length = 100)
        private String polish;

        @Column(name = "Symmetry", length = 100)
        private String symmetry;

        @Column(name = "Flourescence", length = 100)
        private String flourescence;

        @Column(name = "Measurememt", length = 100)
        private String measurememt;

        @Column(name = "ShapeAndCut", length = 100)
        private String shapeAndCut;

        @Column(name = "Dates")
        @Temporal(TemporalType.DATE)
        private Date dates;

        @Column(name = "Number")
        private int number;
    }

