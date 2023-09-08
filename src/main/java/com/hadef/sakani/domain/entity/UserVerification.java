package com.hadef.sakani.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_reservation")
public class UserVerification extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private String code;
    private Long failedAttemps;
}
