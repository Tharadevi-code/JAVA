package com.payment.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.constants.EventTopics;
import com.common.events.OrderCreatedEvent;
import com.common.events.PaymentCompletedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = EventTopics.ORDER_CREATED, groupId = "payment-group")
	public void handleOrder(OrderCreatedEvent event) {
		//Call payment gateway
		boolean success = true; 
		kafkaTemplate.send(EventTopics.PAYMENT_COMPLETED,
				PaymentCompletedEvent.builder().orderId(event.getOrderId()).build());
	}
}
