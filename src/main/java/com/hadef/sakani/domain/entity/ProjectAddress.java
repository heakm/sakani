package com.hadef.sakani.domain.entity;

import com.hadef.sakani.domain.value.enumurator.Country;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAddress {

    @Column(name = "Country_Name")
    private Country country;
    @Column(name = "District_Name")
    private String district;
    @Column(name = "City_Name")
    private String city;
    @Column(name = "Neighbourhood_Name")
    private String neighbourhood;
    @Column(name = "Street_Name")
    private String street;
    @Column(name = "Longitude")
    private String longitude;
    @Column(name = "Latitude")
    private String latitude;

}
