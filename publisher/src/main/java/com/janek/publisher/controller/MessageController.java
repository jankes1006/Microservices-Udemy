package com.janek.publisher.controller;

import com.janek.publisher.services.rabbitmq.RabbitMqService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final RabbitMqService rabbitMqService;

    public MessageController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @GetMapping("notification/{studentId}")
    public String sendNotificationAboutStudent(@PathVariable Long studentId){
        rabbitMqService.sendNotificationAboutStudent(studentId);
        return "Wysłano notyfikację odnośnie studenta!";
    }

}
