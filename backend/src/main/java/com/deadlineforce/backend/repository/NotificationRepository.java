package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findNotificationByUserOwner_Id(long userId, Pageable pageable);
}
