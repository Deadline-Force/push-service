package com.deadlineforce.backend.model.notification;

import com.deadlineforce.backend.security.details.AuthorizationRoles;

public record NotificationDispatch(String userIds, AuthorizationRoles userRole, NotificationSend notification) {
}
