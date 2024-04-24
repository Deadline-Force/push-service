package com.deadlineforce.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recipients")
@Data
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;
}

//JT
