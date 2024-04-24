package com.deadlineforce.backend.entity;

import com.deadlineforce.backend.entity.converter.AuthorizationRolesConverter;
import com.deadlineforce.backend.security.details.AuthorizationRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username, login, password;
    @Column(name = "user_role")
    @Convert(converter = AuthorizationRolesConverter.class)
    private AuthorizationRoles role = AuthorizationRoles.USER;
    @JsonIgnore
    @RestResource(exported = false)
    @OneToMany(mappedBy = "userOwner")
    private List<Notification> createdNotifications;
    @JsonIgnore
    @ManyToMany
    @RestResource(exported = false)
    @JoinTable(
            name = "recipients",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<Notification> receivedNotifications;

    public User(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }
}
