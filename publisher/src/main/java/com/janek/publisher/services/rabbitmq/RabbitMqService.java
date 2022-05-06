package com.janek.publisher.services.rabbitmq;

public interface RabbitMqService {
    void sendNotificationAboutStudent(Long studentId);
}
