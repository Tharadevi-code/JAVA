package com.order.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.common.constants.EventTopics;
import com.common.events.OrderCreatedEvent;
import com.order.entity.Order;
import com.order.repo.OrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void createorder(Order order) {
		try {
			Order savedOrder = orderRepository.save(order);

			kafkaTemplate.send(EventTopics.ORDER_CREATED,
					OrderCreatedEvent.builder().orderId(savedOrder.getId()).customerId(savedOrder.getCustomerId())
							.productId(savedOrder.getProductId()).quantity(savedOrder.getQuantity()).build());

		} catch (Exception e) {

		}

	}

}
