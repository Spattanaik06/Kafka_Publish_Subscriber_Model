package com.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.springboot.binding.AppConfig;

import org.apache.kafka.clients.producer.ProducerConfig;

@Configuration
public class KafkaProducerConfig 
{
	@Bean
	public ProducerFactory< String,Object> producerFactory()
	{
		Map<String,Object> configProds= new HashMap<String, Object>();
		configProds.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.HOST);
		configProds.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProds.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Object>(configProds);
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate()
	{
		return new KafkaTemplate<String, Object>(producerFactory());
	}
}
