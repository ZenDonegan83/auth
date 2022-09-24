package com.inkd.auth.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name="create_time",nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false, length = 50)
    private Role role;

    @Transient
    private String accessToken;

    @Transient
    private String refreshToken;

}
