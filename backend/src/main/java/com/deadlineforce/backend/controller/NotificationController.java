package com.deadlineforce.backend.controller;

import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.model.notification.NotificationModel;
import com.deadlineforce.backend.model.notification.NotificationSend;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationModel notificationModel;

    public NotificationController(NotificationModel notificationModel) {
        this.notificationModel = notificationModel;
    }

    @PostMapping("/send/{userId}")
    public HttpStatus sendNotification(@PathVariable("userId") User user, @RequestBody NotificationSend notification) {
        this.notificationModel.sendNotification(user, notification);
        return HttpStatus.OK;
    }
}
