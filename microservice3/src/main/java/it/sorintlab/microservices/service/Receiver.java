package it.sorintlab.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import it.sorintlab.microservices.model.Customer;
import it.sorintlab.microservices.repository.CustomerRepository;

@Component
public class Receiver {
	
	@Autowired
	private CustomerRepository repository;

	@JmsListener(destination = "customers.events")
    public void receiveMessage(Customer customer) {
        System.out.println("Received <" + customer + ">");
        repository.save(customer);
    }

	
}
