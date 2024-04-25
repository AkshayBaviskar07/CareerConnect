package com.telusko.reviewms.reviews.messaging;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    /**
     * Define a bean for company rating queue.
     * @return A new queue instance for company rating.
     */
    @Bean
    public Queue companyRatingQueue() {
        return new Queue("companyRatingQueue");
    }

    /**
     * Configures and returns a JSON message converter using Jackson library.
     * @return Jackson2JsonMessageConverter instance
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configures and provides a RabbitTemplate for interacting with RabbitMQ.
     * @param connectionFactory the connection factory to be used by the RabbitTemplate
     * @return configured RabbitTemplate instance
     */
    @Bean
    public RabbitTemplate rabbitTemplate (final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
