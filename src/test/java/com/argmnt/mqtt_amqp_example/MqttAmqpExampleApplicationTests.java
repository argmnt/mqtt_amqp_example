package com.argmnt.mqtt_amqp_example;

import com.argmnt.mqtt_amqp_example.listener.ListenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(MqttIntegrationTestConfig.class)
@SpringBootTest
class MqttAmqpExampleApplicationTests {

	@Autowired
	ListenerService listenerService;

	@Autowired
	MqttIntegrationTestConfig.MqttDataSenderGateway mqttDataSenderGateway;

	@Test
	void contextLoads() {
	}

	@Test
	void sendMqttMessage_expectMessageConsumedAndBooleanChanged() throws InterruptedException {
		mqttDataSenderGateway.sendToMqtt("Some Data", "bindingKey");
		Thread.sleep(10000);
		await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
			assertTrue(listenerService.getIsMessageConsumed().get(), "The messge cannot be consumed.");
		});
	}

}
