package com.argmnt.mqtt_amqp_example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue createQueue() {
        return new Queue("exampleQueue");
    }

    @Bean
    public Binding createBindingBetweenQueueAndMqttTopic() {
        return new Binding("exampleQueue", Binding.DestinationType.QUEUE, "amq.topic", "bindingKey", null);
    }
}
