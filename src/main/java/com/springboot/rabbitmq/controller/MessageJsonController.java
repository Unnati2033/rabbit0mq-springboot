/**
 * 
 */
package com.springboot.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rabbitmq.dto.User;
import com.springboot.rabbitmq.publisher.RabbitMQJsonProducer;
import com.springboot.rabbitmq.publisher.RabbitMQProducer;

/**
 * @auther Unnati
 */
@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
	
	private RabbitMQJsonProducer jsonProducer;

	/**
	 * @param jsonProducer
	 */
	public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
		super();
		this.jsonProducer = jsonProducer;
	}
	
	
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		jsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("Json MEssage sent to RabbitMq....");
		
	}

}
