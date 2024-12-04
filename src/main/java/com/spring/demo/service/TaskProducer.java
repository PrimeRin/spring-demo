package com.spring.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaskProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // This will be injected with queue name from properties
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing}")
    private String routingKey;

    public void sendTask(String task) {
        // Send the task message to the RabbitMQ exchange with routing key
        rabbitTemplate.convertAndSend(exchange, routingKey, task);
    }
}
