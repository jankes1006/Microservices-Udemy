package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Dog dog = context.getBean(Dog.class);
        dog.setName("Reksio");
        dog.sayHello();

        Dog dog2 = context.getBean(Dog.class);
        dog2.sayHello();
    }

}
