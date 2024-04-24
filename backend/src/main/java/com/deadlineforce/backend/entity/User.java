package com.deadlineforce.backend.entity;

import com.deadlineforce.backend.security.details.AuthorizationRoles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private AuthorizationRoles role;
}

