package com.example.Client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Value("${exchange}")
    private String exchange;
    @Value("${queue}")
    private String queue;
    @Value("${routeKey}")
    private String routeKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(ClientService.class);
    public void sendRequest(String message){
        logger.info("ClientSide,In service, Sending data to server..");
        rabbitTemplate.convertAndSend(exchange,routeKey,message);
    }

    @RabbitListener(queues = "task-rabbitmq-queue-2")
    public void responseByServer(String message){
        logger.info(message);
    }
}