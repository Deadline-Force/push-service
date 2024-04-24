package com.deadlineforce.backend.model.notification;

import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.repository.NotificationRepository;
import com.deadlineforce.backend.service.UserService;
import com.deadlineforce.backend.service.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class NotificationModel {
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationModel(NotificationRepository notificationRepository, NotificationService notificationService,
                             UserService userService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @Transactional
    public void sendNotification(User recipient, NotificationSend notificationSend) {
        User me = this.userService.getUserFromSecurityContext();
        this.notificationService.sendNotification(me, recipient, notificationSend);
    }
}