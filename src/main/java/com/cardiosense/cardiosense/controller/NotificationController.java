package com.cardiosense.cardiosense.controller;

import com.cardiosense.cardiosense.model.User.Notification;
import com.cardiosense.cardiosense.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<?> sendNotification() {
        notificationService.sendNotifications();
        Map<String, String> response = Map.of("message", "Notifications sent");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/markAsRead/{notificationId}")
    public ResponseEntity<?> markAsRead(@PathVariable String notificationId) {
        notificationService.markAsRead(notificationId);
        Map<String, String> response = Map.of("message", "Notification marked as read");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable String userId) {
        return notificationService.getNotificationsByUser(userId);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllNotifications() {
        notificationService.deleteAllNotifications();
        Map<String, String> response = Map.of("message", "All notifications deleted");
        return ResponseEntity.ok(response);
    }
}
