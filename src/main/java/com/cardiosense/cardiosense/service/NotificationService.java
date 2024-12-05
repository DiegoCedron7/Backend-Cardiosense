package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.User.Notification;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public void sendNotifications() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);

        List<UserEntity> users = userService.getAllUsers();
        for (UserEntity user : users) {
            Notification notification = new Notification();
            notification.setUserId(user.getId());
            notification.setRead(false);
            notification.setTitle("Cambia tu peso");
            notification.setMessage("Recuerda que debes cambiar tu peso semanalmente");
            notification.setDate(formattedDate);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getNotificationsByUser(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }
}
