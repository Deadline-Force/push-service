package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @RestResource(exported = false)
    Page<Notification> findNotificationByUserOwner_Id(long userId, Pageable pageable);
}
