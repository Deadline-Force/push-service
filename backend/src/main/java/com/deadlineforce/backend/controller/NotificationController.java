package com.deadlineforce.backend.controller;

import com.deadlineforce.backend.entity.Notification;
import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.model.notification.NotificationModel;
import com.deadlineforce.backend.model.notification.NotificationSend;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@Tag(name = "NotificationController")
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

    @GetMapping("/my")
    public List<Notification> createdNotifications(@RequestParam int page, @RequestParam int size) {
        return this.notificationModel.getCreatedNotifications(page, size);
    }
}
