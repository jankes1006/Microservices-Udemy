package com.janek.receiver.controller;

import com.janek.receiver.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/message")
    public String receiverMessage() {
        Object message = rabbitTemplate.receiveAndConvert("kurs");
        if (Objects.nonNull(message))
            return "Udało się pobrać wiadomość: " + message.toString();
        return "Nie ma żadnej wiadomości do odczytu!";
    }

    @GetMapping("/notification")
    public ResponseEntity<Notification> receiveNotification() {
        Notification notification = rabbitTemplate.receiveAndConvert("kurs", ParameterizedTypeReference.forType(Notification.class));
        if (Objects.nonNull(notification)) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.noContent().build();
    }

    @RabbitListener(queues = "kurs")
    public void listenerMessage(Notification notification) {
        System.out.println(notification.getTitle() + " " + notification.getBody() + " " + notification.getEmail());
    }
}
