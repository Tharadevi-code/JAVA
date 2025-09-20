package com.order.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.constants.EventTopics;
import com.common.events.OrderCreatedEvent;
import com.order.entity.Order;
import com.order.repo.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void createorder(Order order) {
		try {
			Order savedOrder = orderRepository.save(order);

			kafkaTemplate.send(EventTopics.ORDER_CREATED,
					OrderCreatedEvent.builder().orderId(savedOrder.getId()).customerId(savedOrder.getCustomerId())
							.productId(savedOrder.getProductId()).price(savedOrder.getPrice())
							.quantity(savedOrder.getQuantity()).build());

		} catch (Exception e) {
			log.info("Failed to place order {}", e);
		}
	}

	@KafkaListener(topics = { EventTopics.PAYMENT_FAILED, EventTopics.INVENTORY_FAILED,
			"shipping-failed" }, groupId = "order-service-group")
	public void cancelOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow();
		order.setStatus("CANCELLED");
		orderRepository.save(order);
		kafkaTemplate.send("order-cancelled", orderId);
	}

}
