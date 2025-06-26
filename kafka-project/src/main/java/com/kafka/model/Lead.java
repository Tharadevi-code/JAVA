package com.kafka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "leads")
public class Lead {
	@Id
	private Integer id;
	private String name;
	private String phone;
	private String source;
}
