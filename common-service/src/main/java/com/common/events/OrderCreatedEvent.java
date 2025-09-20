package com.common.events;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
//	private Integer id;
	private Integer orderId;
	private Integer customerId;
	private BigDecimal price;
	private Integer productId;
	private int quantity;
}
