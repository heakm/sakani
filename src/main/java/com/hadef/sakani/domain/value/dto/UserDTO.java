package com.hadef.sakani.domain.value.dto;

import com.hadef.sakani.domain.entity.Project;
import com.hadef.sakani.domain.entity.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String name;

    private String email;
    private String username;
    private String password;

    private Set<Project> project = new HashSet<>();

    private Set<Reservation> reservations = new HashSet<>();

    private boolean isSubscribedToNewsletter;
    private boolean isActive;
    private boolean isApproved;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String username, String password, Set<Project> project, Set<Reservation> reservations, boolean isSubscribedToNewsletter, boolean isActive, boolean isApproved) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.project = project;
        this.reservations = reservations;
        this.isSubscribedToNewsletter = isSubscribedToNewsletter;
        this.isActive = isActive;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<Project> getProject() {
        return project;
    }

    public UserDTO setProject(Set<Project> project) {
        this.project = project;
        return this;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public UserDTO setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public boolean isSubscribedToNewsletter() {
        return isSubscribedToNewsletter;
    }

    public UserDTO setSubscribedToNewsletter(boolean subscribedToNewsletter) {
        isSubscribedToNewsletter = subscribedToNewsletter;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public UserDTO setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
}
