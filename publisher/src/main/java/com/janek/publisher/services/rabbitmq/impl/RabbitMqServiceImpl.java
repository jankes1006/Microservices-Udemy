package com.janek.publisher.services.rabbitmq.impl;

import com.janek.publisher.services.rabbitmq.RabbitMqService;
import com.janek.publisher.services.student.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
class RabbitMqServiceImpl implements RabbitMqService {

    private final StudentService studentService;
    private final RabbitTemplate rabbitTemplate;

    private static final String nameQue = "kurs";

    public RabbitMqServiceImpl(StudentService studentService, RabbitTemplate rabbitTemplate) {
        this.studentService = studentService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNotificationAboutStudent(Long studentId) {
        rabbitTemplate.convertAndSend(nameQue, studentService.getNotificationAboutStudent(studentId));
    }
}
