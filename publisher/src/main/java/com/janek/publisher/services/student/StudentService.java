package com.janek.publisher.services.student;

import com.janek.publisher.model.Notification;

public interface StudentService {
    Notification getNotificationAboutStudent(Long id);
}
