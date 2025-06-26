package com.kafka.service;

import com.kafka.model.Lead;

public interface LeadProducerService {
	void sendLead(Lead lead);
}
