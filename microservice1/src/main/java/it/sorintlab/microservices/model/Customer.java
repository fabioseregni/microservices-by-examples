package it.sorintlab.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private Long id;
	private String lastName;
	private String firstName;
	
}
