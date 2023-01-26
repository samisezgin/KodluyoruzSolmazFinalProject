package com.samisezgin.notificationservice.model.concrete;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;

public class SmsNotification extends Notification {

    public SmsNotification() {
    }

    public SmsNotification(String message, String contactInfo) {
        this.setNotificationType(NotificationType.SMS);
        this.setNotificationMessage(message);
        this.setContactInfo(contactInfo);
    }

}
