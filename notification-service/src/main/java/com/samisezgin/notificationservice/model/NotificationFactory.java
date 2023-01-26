package com.samisezgin.notificationservice.model;


import com.samisezgin.notificationservice.model.concrete.EmailNotification;
import com.samisezgin.notificationservice.model.concrete.PushNotification;
import com.samisezgin.notificationservice.model.concrete.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    public Notification getNotification(String notificationType) {

        if (notificationType == null) {
            return null;
        }

        if (notificationType.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        } else if (notificationType.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (notificationType.equalsIgnoreCase("PUSH")) {
            return new PushNotification();
        }

        return null;
    }
}
