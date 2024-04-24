package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.Notification;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@Tag(name = "NotificationController")
@RepositoryRestResource(path = "notifications", itemResourceRel = "notifications")
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @RestResource(exported = false)
    Page<Notification> findNotificationsByUserOwner_Id(long userId, Pageable pageable);
}
