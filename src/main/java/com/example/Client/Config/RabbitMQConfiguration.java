package com.example.Client.Config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class RabbitMQConfiguration {

    @Value("${exchange}")
    private String exchange;
    @Value("${queue}")
    private String queue;
    @Value("${routeKey}")
    private String routeKey;

    @Bean
    public Queue buildQueue(){
        return new Queue(queue);
    }
    @Bean
    public Queue buildListenerQueue(){
        return new Queue("task-rabbitmq-queue-2");
    }
    @Bean
    public TopicExchange buildExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindQueueAndExchange(){
        return BindingBuilder
                .bind(buildQueue())
                .to(buildExchange())
                .with(routeKey);
    }
}
