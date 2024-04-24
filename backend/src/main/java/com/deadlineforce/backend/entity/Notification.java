package com.deadlineforce.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Date;

@Data
@Entity
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
    private Date createdAt;
}