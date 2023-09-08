package com.hadef.sakani.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    private String countryCode;
    private String phoneNumber;
}
