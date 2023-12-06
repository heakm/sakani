package com.hadef.sakani.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Reservation extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean agreeTerms;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "reservation",
            cascade = CascadeType.ALL
    )
    private ReservationStatus reservationStatus;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_reservation_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "reservation",
            cascade = CascadeType.ALL
    )
    private Project project;

    public Reservation() {
    }

    public Reservation(Long id, boolean agreeTerms, ReservationStatus reservationStatus, User user) {
        this.id = id;
        this.agreeTerms = agreeTerms;
        this.reservationStatus = reservationStatus;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return agreeTerms == that.agreeTerms && Objects.equals(id, that.id) && Objects.equals(reservationStatus, that.reservationStatus) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agreeTerms, reservationStatus, user);
    }

    public Long getId() {
        return id;
    }

    public Reservation setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isAgreeTerms() {
        return agreeTerms;
    }

    public Reservation setAgreeTerms(boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
        return this;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public Reservation setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Reservation setUser(User user) {
        this.user = user;
        return this;
    }
}
