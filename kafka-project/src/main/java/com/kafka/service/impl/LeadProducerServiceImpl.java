//package com.kafka.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import com.kafka.model.Lead;
//import com.kafka.service.LeadProducerService;
//
//@Service
//public class LeadProducerServiceImpl implements LeadProducerService {
//
//	private static final String TOPIC = "lead-topic";
//
//	@Autowired
//	private KafkaTemplate<String, Lead> kafkaTemplate;
//
//	@Override
//	public void sendLead(Lead lead) {
//		kafkaTemplate.send(TOPIC, lead);
//	}
//
//}
