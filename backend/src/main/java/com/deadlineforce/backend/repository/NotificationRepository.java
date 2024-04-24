package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByUserId(Long userId, Pageable pageable);

    Page<Notification> findAll(Pageable pageable);

    Page<Notification> findNotificationByUserOwner_Id(long userId, Pageable pageable);

    Page<Notification> findNotificationsByUserIdAndMessageContaining(long userId, String keyword, Pageable pageable);
}
