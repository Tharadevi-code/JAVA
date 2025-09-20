package com.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.order.entity.Order;
import com.order.service.impl.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<String> createOrder(@RequestBody Order order) {
		log.info("Creating order id {]", order.getProductId());
		orderService.createorder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");

	}

}
