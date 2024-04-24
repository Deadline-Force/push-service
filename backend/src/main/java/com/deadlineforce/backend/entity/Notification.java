package com.deadlineforce.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title, message;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @RestResource(exported = false)
    private User userOwner;
    private Date createdAt = new Date(System.currentTimeMillis());

    public Notification(String title, String message, User userOwner) {
        this.title = title;
        this.message = message;
        this.userOwner = userOwner;
    }
}