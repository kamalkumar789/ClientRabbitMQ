package com.example.Client.Controller;

import com.example.Client.Service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;
    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    @PostMapping(value = "/add/user/{value}")
    public ResponseEntity<String> sendObjectToServer(@RequestParam String value){
        logger.info("ClientSide, Sending data to server..");
        try{
            clientService.sendRequest(value);
        }catch (Exception e){
            logger.info("Queue is not responding..");
            e.printStackTrace();
            return new ResponseEntity<>("Queue is not working",HttpStatus.NOT_FOUND);
        }
        logger.info("Data sent to queue..");
        return new ResponseEntity<>("Message has been sent to server..",HttpStatus.OK);
    }
}
