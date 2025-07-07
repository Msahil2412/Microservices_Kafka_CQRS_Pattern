package com.bankrecords.records.Consumers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.bankrecords.records.api.TopicLog;
import com.bankrecords.records.infrastructure.BankRecordsConsume;

@Service
public class TopicLoggingConsumer {
    @Autowired
    private BankRecordsConsume topicLogRepository;

    @KafkaListener(topics = { "AccountOpenedEvent", "FundsDepositedEvent", "FundsWithdrawnEvent",
            "AccountClosedEvent" }, groupId = "topic-logger-group")
    public void consumeAllTopics(Object event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        topicLogRepository.save(new TopicLog(topic, new Date()));
    }
}