package com.samisezgin.notificationservice.listener;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

//    private final NotificationFactory notificationFactory;

    private final NotificationRepository repository;

    public NotificationListener(NotificationRepository repository) {
        this.repository = repository;
    }

//    public NotificationListener(NotificationFactory notificationFactory) {
//        this.notificationFactory = notificationFactory;
//    }

    @RabbitListener(queues = "notification")
    public void notificationListener(Notification notification) {
        repository.save(notification);
//       var notification1 = notificationFactory.getNotification(notification.getNotificationType().toString());
//       notification1.setNotificationMessage(notification.getNotificationMessage());
//       notification1.setNotificationType(notification.getNotificationType());
//       notification1.setContactInfo(notification.getContactInfo());

    }

}