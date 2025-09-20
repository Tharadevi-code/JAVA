package com.payment.service;

import java.math.BigDecimal;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.constants.EventTopics;
import com.common.events.OrderCreatedEvent;
import com.common.events.PaymentCompletedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = EventTopics.ORDER_CREATED)
	public void processPayment(OrderCreatedEvent event) {
		if (chargeCard(event.getPrice())) {
			kafkaTemplate.send(EventTopics.PAYMENT_COMPLETED, new PaymentCompletedEvent(event.getOrderId(), event.getPrice()));
		} else {
			log.info("Payment failed");
			kafkaTemplate.send(EventTopics.PAYMENT_FAILED, event.getOrderId());
		}
	}

	private boolean chargeCard(BigDecimal amount) {
		// simulate gateway logic
		return true;
	}
}
