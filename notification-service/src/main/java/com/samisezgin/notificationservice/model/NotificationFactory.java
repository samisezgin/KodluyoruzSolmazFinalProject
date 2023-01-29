package com.samisezgin.notificationservice.model;


import com.samisezgin.notificationservice.model.concrete.EmailNotification;
import com.samisezgin.notificationservice.model.concrete.PushNotification;
import com.samisezgin.notificationservice.model.concrete.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    public Notification getNotification(String notificationType, String contactInfo) {

        if (notificationType == null) {
            return null;
        }

        if (notificationType.equalsIgnoreCase("SMS")) {
            SmsNotification smsNotification=new SmsNotification();
            smsNotification.setPhoneNumber(contactInfo);
            return smsNotification;
        } else if (notificationType.equalsIgnoreCase("EMAIL")) {
            EmailNotification emailNotification=new EmailNotification();
            emailNotification.setEmailAddress(contactInfo);
            return emailNotification;
        } else if (notificationType.equalsIgnoreCase("PUSH")) {
            PushNotification pushNotification=new PushNotification();
            pushNotification.setUsername(contactInfo);
            return pushNotification;
        }

        return null;
    }
}
