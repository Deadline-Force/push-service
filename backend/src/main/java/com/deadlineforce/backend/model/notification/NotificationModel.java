package com.deadlineforce.backend.model.notification;

import com.deadlineforce.backend.entity.Notification;
import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.repository.NotificationRepository;
import com.deadlineforce.backend.service.UserService;
import com.deadlineforce.backend.service.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Notification> getCreatedNotifications(int page, int size) {
        Page<Notification> pageNotification = this.notificationRepository
                .findNotificationsByUserOwner_Id(this.userService.getUserIdFromSecurityContext(),
                        PageRequest.of(page, size));
        return pageNotification.getContent();
    }

    public List<Notification> getReceivedNotifications(int page, int size) {
        return this.userService.getUserFromSecurityContext().getReceivedNotifications()
                .stream()
                .skip((long) page*size)
                .limit(size)
                .toList();
    }
}