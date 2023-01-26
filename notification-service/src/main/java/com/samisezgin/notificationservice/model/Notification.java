package com.samisezgin.notificationservice.model;

import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Document(value = "notifications")
public abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String notificationMessage;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;



    public Notification() {
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


    @Override
    public String toString() {
        return "Notification{" +
                "notificationMessage='" + notificationMessage + '\'' +
                ", notificationType=" + notificationType +
                '}';
    }
}
