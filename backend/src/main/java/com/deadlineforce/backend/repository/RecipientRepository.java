package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.Recipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    Page<Recipient> findByUserId(Long userId, Pageable pageable);

    Page<Recipient> findByNotificationId(Long notificationId, Pageable pageable);
}

