package com.techbank.account.query.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.FundsWithdrawnEvent;
import com.techbank.account.query.infrastructure.handlers.EventHandler;

@Service
public class AccountEventConsumer implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountOpenedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload FundsDepositedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountClosedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
