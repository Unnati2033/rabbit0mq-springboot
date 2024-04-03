/**
 * 
 */
package com.springboot.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @auther Unnati
 */
@Configuration
public class RabbitMQConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.jsonqueue.name}")
	private String jsonqueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	//spring bean for rabbitmq queue
	@Bean
	public Queue  queue() {
		return new Queue(queue);
	}
	
	//Spring bean for Queue(store json message)
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonqueue);
	}
	//spring bean for rabbitmq exchnage
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	//binding between queue and exchange using routing key
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}
	
	//binding between json queue and exchange using routing key
	
	@Bean
	public Binding jsonbinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	//ConnectionFactory
	//RabbitTemplate
	//RabbitAdmin spring boot autoconfiguration these 3 bean configure automatically
}
