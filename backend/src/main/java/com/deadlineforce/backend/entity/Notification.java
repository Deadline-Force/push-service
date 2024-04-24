package com.deadlineforce.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userOwner;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}