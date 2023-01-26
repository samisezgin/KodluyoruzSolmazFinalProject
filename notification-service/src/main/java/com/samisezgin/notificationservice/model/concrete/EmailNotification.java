package com.samisezgin.notificationservice.model.concrete;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;

public class EmailNotification extends Notification {

    private String emailAddress;
    public EmailNotification() {
        this.setNotificationType(NotificationType.EMAIL);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
