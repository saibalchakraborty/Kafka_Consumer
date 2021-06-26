package com.example.demo.consuer.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.consuer.kafka.model.Transaction;
import com.example.demo.consuer.kafka.repository.TransactionRepository;

@Service
public class KafkaMessageListener {
	@Autowired
	private TransactionRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageListener.class);
	
	@KafkaListener(topics = "quickstart-events", groupId = "demo-group-id", containerFactory = "kafkaListenerContainerFactory")
	public void consume(Transaction transaction) {
		try {
		repository.save(transaction);
		}
		catch(Exception e) {
			LOGGER.error("Adding transaction details failed : {}", e.getMessage());
		}
	}
		
}
