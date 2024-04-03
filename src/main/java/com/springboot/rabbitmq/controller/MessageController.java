/**
 * 
 */
package com.springboot.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rabbitmq.publisher.RabbitMQProducer;

/**
 * @auther Unnati
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	private RabbitMQProducer producer;

	/**
	 * @param producer
	 */
	public MessageController(RabbitMQProducer producer) {
		this.producer = producer;
	}

	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		producer.sendMessage(message);
		return ResponseEntity.ok("Message sent to RabbitMQ..");
		
	}

}
