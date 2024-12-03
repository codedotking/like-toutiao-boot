package com.hongyan.toutiao.model.db;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 191)
    private String name;

    @Column(name = "description", length = 191)
    private String description;

    @Column(name = "verified_content", length = 191)
    private String verifiedContent;

    @Column(name = "user_verified")
    private Boolean userVerified;

    @Column(name = "follower_count", nullable = false)
    private int followerCount;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "email", length = 191)
    private String email;

    @Column(name = "password", length = 191)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "user_id", nullable = false, length = 191)
    private String userId;
}
