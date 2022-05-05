package com.janek.publisher.controller;

import com.janek.publisher.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/message")
    public String sendMessage(String message) {
        rabbitTemplate.convertAndSend("kurs", message);
        return "Wrzucono wiadomość na RabbitMq";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody Notification notification){
        rabbitTemplate.convertAndSend("kurs", notification);
        return "Wrzucono notyfikację na RabbitMq";
    }
}
