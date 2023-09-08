package com.hadef.sakani.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProjectExternal {

    private String availabilityOfElevator;
    private String noOfElevator;
    private String availabilityOfParking;
    private String noOfParking;
}
