package com.janek.publisher.services.student.impl;

import com.janek.publisher.model.Notification;
import com.janek.publisher.model.Student;
import com.janek.publisher.services.student.StudentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.lang.String.format;

@Service
class StudentServiceImpl implements StudentService {

    private final String titleNotification = "Witaj %s";
    private final String bodyNotification = "Miło że z nami jesteś %s %s";
    private final String studentApiAddress = "http://localhost:8080/students/";
    private final RestTemplate restTemplate;

    public StudentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Notification getNotificationAboutStudent(Long studentId) {
        Student student = restTemplate.exchange(studentApiAddress + studentId, HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();
        return createNotification(student);
    }

    private Notification createNotification(Student student) {
        if (Objects.isNull(student)) {
            return null;
        }
        Notification notification = new Notification();
        notification.setEmail(student.getEmail());
        notification.setTitle(format(titleNotification, student.getFirstName()));
        notification.setBody(format(bodyNotification, student.getFirstName(), student.getLastName()));
        return notification;
    }

}
