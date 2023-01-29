package com.samisezgin.notificationservice.listener;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.NotificationFactory;
import com.samisezgin.notificationservice.model.NotificationRequest;
import com.samisezgin.notificationservice.service.impl.NotificationServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final NotificationFactory notificationFactory;

    private final NotificationServiceImpl notificationService;


    public NotificationListener(NotificationFactory notificationFactory, NotificationServiceImpl notificationService) {
        this.notificationFactory = notificationFactory;
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${booking.rabbitmq.queue}")
    public void notificationListener(NotificationRequest notificationRequest) {
        System.out.println("Notification received: " + notificationRequest);

        Notification notification = notificationFactory.getNotification(notificationRequest.getNotificationType(), notificationRequest.getContactInfo());

        notification.setNotificationMessage(notificationRequest.getNotificationMessage());
        notificationService.save(notification);
        System.out.println(notification.getNotificationType() + " notification sent.\n" + notification);

    }

}