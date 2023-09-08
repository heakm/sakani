package com.hadef.sakani.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_reservation_status")
public class ReservationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationStatus that = (ReservationStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(reservation, that.reservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reservation);
    }

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "reservation_status_reservation",
            joinColumns = @JoinColumn(name = "reservation_status_null"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public ReservationStatus setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReservationStatus setName(String name) {
        this.name = name;
        return this;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public ReservationStatus setReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }
}