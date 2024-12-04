package com.spring.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing}")
    private String routingKey;

    // Define the queue
    @Bean
    public Queue taskQueue() {
        // Durable queue with name from properties
        return new Queue(queueName, true);
    }

    // Define the exchange
    @Bean
    public DirectExchange taskExchange() {
        // Create a direct exchange with name from properties
        return new DirectExchange(exchangeName);
    }

    // Define the binding between the queue and exchange with a routing key
    @Bean
    public Binding taskBinding() {
        // Binding the queue to the exchange with routing key from properties
        return BindingBuilder.bind(taskQueue()).to(taskExchange()).with(routingKey);
    }
}
