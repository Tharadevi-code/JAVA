package com.kafka.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lead")
public class Lead {
	private String id;
	private String name;
	private String phone;
	private String source;
}
