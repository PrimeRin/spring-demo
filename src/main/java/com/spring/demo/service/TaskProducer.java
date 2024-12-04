package com.spring.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaskProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // If you only know the queue name, use the queue name as the routing key
    @Value("${rabbitmq.queue-name}")
    private String queueName;

    public void sendTask(String task) {
        // Use the queue name as the routing key
        rabbitTemplate.convertAndSend("", queueName, task);
    }
}
