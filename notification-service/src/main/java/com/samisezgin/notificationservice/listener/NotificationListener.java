package com.samisezgin.notificationservice.listener;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.NotificationFactory;
import com.samisezgin.notificationservice.model.NotificationRequest;
import com.samisezgin.notificationservice.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final NotificationFactory notificationFactory;

    private final NotificationRepository repository;



    public NotificationListener(NotificationFactory notificationFactory, NotificationRepository repository) {
        this.notificationFactory = notificationFactory;
        this.repository = repository;
    }

    @RabbitListener(queues = "notification")
    public void notificationListener(NotificationRequest notificationRequest) {
        System.out.println("Notification received: "+ notificationRequest);
        Notification notification = notificationFactory.getNotification(notificationRequest.getNotificationType(), notificationRequest.getContactInfo());
        notification.setNotificationMessage(notificationRequest.getNotificationMessage());
        repository.save(notification);
        System.out.println(notification.getNotificationType()+" notification sent.\n"+ notification);

    }

}