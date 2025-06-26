package com.kafka.service;

import com.kafka.model.Lead;

public interface LeadConsumerService {
	void consumeLead(Lead lead);
}
