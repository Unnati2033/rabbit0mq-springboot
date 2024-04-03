/**
 * 
 */
package com.springboot.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springboot.rabbitmq.dto.User;

/**
 * @auther Unnati
 */
@Service
public class RabbitMQJsonConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.jsonqueue.name}"})
	public void consumeMessage(User user) {
		
		
		
		LOGGER.info(String.format("Recived json message ->%s", user.toString()));
		
	}
	

}
