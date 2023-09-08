package com.hadef.sakani.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProjectDetails {

    private String usingFor;
    private String propertyType;
    private String theSpace;
    private String landNumber;
    private String planNumber;
    private String noOfUnits;
    private String floorNumber;
    private String unitNumber;
    private String roomsNumber;
    private String roomsType;
    private String realEstateFacade;
    private String propertyLimitsAndLength;
    private String streetWidth;
    private String constructionDate;
    private String restrictionAndMortgage;
    private String obligations;
    private Float rentalPrice;
    private Float sellingPrice;
    private Float sellingMeterPrice;
    private String propertyDispute;
}
