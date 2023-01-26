package com.samisezgin.notificationservice.model.concrete;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.model.concrete.enums.NotificationType;

public class SmsNotification extends Notification {

    private String phoneNumber;
    public SmsNotification() {
        this.setNotificationType(NotificationType.SMS);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
