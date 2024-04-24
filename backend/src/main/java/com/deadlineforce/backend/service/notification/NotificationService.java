package com.deadlineforce.backend.service.notification;

import com.deadlineforce.backend.entity.Notification;
import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.model.notification.NotificationSend;
import com.deadlineforce.backend.repository.NotificationRepository;
import com.deadlineforce.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public NotificationService(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void sendNotification(User sender, User recipient, NotificationSend notificationSend) {
        Notification notification = new Notification(notificationSend.title(), notificationSend.message(), sender);
        this.notificationRepository.save(notification);

        sender.getCreatedNotifications().add(notification);
        recipient.getReceivedNotifications().add(notification);

        this.notificationRepository.save(notification);
        this.userRepository.save(sender);
        this.userRepository.save(recipient);
    }
}
