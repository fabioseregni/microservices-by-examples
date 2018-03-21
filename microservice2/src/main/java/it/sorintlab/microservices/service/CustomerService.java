package it.sorintlab.microservices.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.sorintlab.microservices.model.Customer;
import it.sorintlab.microservices.repository.CustomerRepository;

@Component
public class CustomerService {

	private static Logger log = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Cacheable("customers")
	public List<Customer> findByLastName(final String lastName) {
		log.info("calling find customer with parameter lastName=" + lastName);
		randomSleep();
		return customerRepository.findByLastName(lastName);
	}

	public Customer save(final Customer customer) {
		Customer saved = customerRepository.save(customer);
		jmsTemplate.convertAndSend("customers.events", customer);
		return saved;
	}

	private void randomSleep() {
		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;
		if (randomNum == 3) {
			log.error("Problem durig get data...");
			sleep(1500);
		}
	}

	private void sleep(final int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
