package com.samisezgin.finalproject.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String notificationMessage;
    private String notificationType;

    private String contactInfo;

    public Notification() {
    }

    public Notification(String notificationMessage, String notificationType, String contactInfo) {
        this.notificationMessage = notificationMessage;
        this.notificationType = notificationType;
        this.contactInfo = contactInfo;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
