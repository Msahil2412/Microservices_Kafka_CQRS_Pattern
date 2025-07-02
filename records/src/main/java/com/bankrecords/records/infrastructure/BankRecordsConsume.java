package com.bankrecords.records.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bankrecords.records.api.TopicLog;
public interface BankRecordsConsume extends MongoRepository<TopicLog, String> {} 
