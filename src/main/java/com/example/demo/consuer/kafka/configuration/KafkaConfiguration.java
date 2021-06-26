package com.example.demo.consuer.kafka.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.example.demo.consuer.kafka.model.Transaction;

@Configuration
public class KafkaConfiguration {
	
	@Value("${kafka.server}")
	private String kafkaServer;
	
	@Value("${kafka.group.id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, Transaction> kafkaConsumerFactory() {
		
		JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
		
		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		configuration.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		return new DefaultKafkaConsumerFactory<>(configuration, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Transaction> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Transaction> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(kafkaConsumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}

}
