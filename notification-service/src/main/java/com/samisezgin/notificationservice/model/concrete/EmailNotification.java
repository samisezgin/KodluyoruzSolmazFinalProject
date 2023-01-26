package com.samisezgin.notificationservice.model.concrete;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;

public class EmailNotification extends Notification {
    public EmailNotification() {
    }

    public EmailNotification(String message,String contactInfo) {
        this.setNotificationType(NotificationType.EMAIL);
        this.setNotificationMessage(message);
        this.setContactInfo(contactInfo);
    }

}
