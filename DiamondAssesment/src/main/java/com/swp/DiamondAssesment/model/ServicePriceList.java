package com.swp.DiamondAssesment.model;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicePriceList")
@Builder
public class ServicePriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceListID")
    private int priceListId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Service service;

    @Column(name = "SizeFrom", nullable = false, precision = 18, scale = 3)
    private BigDecimal sizeFrom;

    @Column(name = "SizeTo", nullable = false, precision = 18, scale = 3)
    private BigDecimal sizeTo;

    @Column(name = "InitPrice", nullable = false)
    private int initPrice;

    @Column(name = "PriceUnit", nullable = false)
    private int priceUnits;
}
