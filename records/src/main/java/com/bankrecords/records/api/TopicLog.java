package com.bankrecords.records.api;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "topic_logs")
public class TopicLog {
    @Id
    private String id;
    private String topicName;
    private Date receivedAt;

    public TopicLog(String topicName, Date receivedAt) {
        this.topicName = topicName;
        this.receivedAt = receivedAt;
    }

    // getters and setters
}