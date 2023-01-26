package com.samisezgin.notificationservice.model.concrete;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;

public class PushNotification extends Notification {
    public PushNotification() {
    }

    public PushNotification(String message, String contactInfo) {
        this.setNotificationType(NotificationType.PUSH);
        this.setNotificationMessage(message);
        this.setContactInfo(contactInfo);
    }





}
