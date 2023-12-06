package com.hadef.sakani.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tbl_users")
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private byte[] storedHash;
    private byte[] storedSalt;
    private String mobilePhone;
    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
//    @JsonManagedReference(value = "userProjectReference")
    private Set<Project> project = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
//    @JsonManagedReference(value = "userReservationReference")
    private Set<Reservation> reservations = new HashSet<>();

    private boolean isSubscribedToNewsletter = true;
    private boolean isActive = false;
    private boolean isApproved = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isSubscribedToNewsletter == user.isSubscribedToNewsletter && isActive == user.isActive && isApproved == user.isApproved && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Arrays.equals(storedHash, user.storedHash) && Arrays.equals(storedSalt, user.storedSalt) && Objects.equals(project, user.project) && Objects.equals(reservations, user.reservations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, username, project, reservations, isSubscribedToNewsletter, isActive, isApproved);
        result = 31 * result + Arrays.hashCode(storedHash);
        result = 31 * result + Arrays.hashCode(storedSalt);
        return result;
    }

    public User() {
    }

    public byte[] getStoredHash() {
        return storedHash;
    }

    public User setStoredHash(byte[] storedHash) {
        this.storedHash = storedHash;
        return this;
    }

    public byte[] getStoredSalt() {
        return storedSalt;
    }

    public User setStoredSalt(byte[] storedSalt) {
        this.storedSalt = storedSalt;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public User setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }


    public Set<Project> getProject() {
        return project;
    }

    public User setProject(Set<Project> project) {
        this.project = project;
        return this;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public User setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public boolean isSubscribedToNewsletter() {
        return isSubscribedToNewsletter;
    }

    public User setSubscribedToNewsletter(boolean subscribedToNewsletter) {
        isSubscribedToNewsletter = subscribedToNewsletter;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public User setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
