package com.chengshi.train.websocket.controller;

import com.chengshi.train.websocket.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author tjshan
 */
@Controller
public class GreetingController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello1")
    @SendTo("/topic/greetings")
    public Message greeting(Message message) {
        return message;
    }

    @MessageMapping("/hello2")
    public void greeting2(Message message) {
        messagingTemplate.convertAndSend("/topic.greetings", message);
    }
}
