package com.samisezgin.notificationservice.service.impl;

import com.samisezgin.notificationservice.model.Notification;
import com.samisezgin.notificationservice.repository.NotificationRepository;
import com.samisezgin.notificationservice.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
}
