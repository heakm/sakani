package com.hadef.sakani.domain.entity;


import com.hadef.sakani.domain.value.enumurator.Country;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private Country country;
    private String state;
    private String city;
    private String address;

    public Address() {
    }

    public Address(Country country, String state, String city, String address) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public Address setCountry(Country country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }
}
