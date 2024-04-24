package com.deadlineforce.backend.entity;

import com.deadlineforce.backend.entity.converter.AuthorizationRolesConverter;
import com.deadlineforce.backend.security.details.AuthorizationRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username, login, password;
    @Column(name = "user_role")
    @Convert(converter = AuthorizationRolesConverter.class)
    private AuthorizationRoles role;
    @RestResource(exported = false)
    @OneToMany(mappedBy = "userOwner")
    private List<Notification> createdNotifications;
    @ManyToMany
    @RestResource(exported = false)
    @JoinTable(
            name = "recipients",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<Notification> receivedNotifications;
}
