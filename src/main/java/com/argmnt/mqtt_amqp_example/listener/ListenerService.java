package com.argmnt.mqtt_amqp_example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ListenerService {

    private final Logger log;

    private AtomicBoolean isMessageConsumed;

    public ListenerService() {
        this.log = LoggerFactory.getLogger(ListenerService.class);
        this.isMessageConsumed = new AtomicBoolean(false);
    }

    public AtomicBoolean getIsMessageConsumed() {
        return isMessageConsumed;
    }

    @RabbitListener(queues = "exampleQueue")
    public void listenMessage(String message) {
        log.info("Event consumed : " + message);
        isMessageConsumed.set(true);
    }
}
