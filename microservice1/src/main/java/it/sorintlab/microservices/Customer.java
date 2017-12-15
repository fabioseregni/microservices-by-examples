package it.sorintlab.microservices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;

	protected Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}
