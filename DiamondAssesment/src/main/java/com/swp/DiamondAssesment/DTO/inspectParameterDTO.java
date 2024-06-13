package com.swp.DiamondAssesment.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class inspectParameterDTO {
    //khong can id vi id tu duoc tao trong database
    private Boolean isDiamond;
    private String comments;
    private BigDecimal depth;
    private BigDecimal tablee;
    private BigDecimal crowAngle;
    private BigDecimal crowHeight;
    private BigDecimal pavillionAngle;
    private BigDecimal pavillionDepth;
    private BigDecimal starLength;
    private BigDecimal lowerHalf;
    private String girdle;
    private BigDecimal caratWeight;
    private String colorGrade;
    private String clarityGrade;
    private String cutGrade;
    private String proportions;
    private String polish;
    private String symmetry;
    private String flourescence;
    private String measurememt;
    private String shapeAndCut;
    private Date dates;
    private Integer number;

}