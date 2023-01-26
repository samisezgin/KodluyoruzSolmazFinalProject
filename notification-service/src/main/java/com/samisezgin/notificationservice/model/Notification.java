package com.samisezgin.notificationservice.model;

import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Document(value = "notifications")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String notificationMessage;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private String contactInfo;

    public Notification() {
    }

    public Notification(String notificationMessage, NotificationType notificationType) {
        this.notificationMessage = notificationMessage;
        this.notificationType = notificationType;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationMessage='" + notificationMessage + '\'' +
                ", notificationType=" + notificationType +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
