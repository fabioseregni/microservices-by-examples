package it.sorintlab.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.microservices.Application;
import it.sorintlab.microservices.model.Customer;
import it.sorintlab.microservices.service.Microservice2Service;

@RestController
public class CustomerController {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Microservice2Service microservice2Service;

	@GetMapping("/customers")
	public @ResponseBody Customer[] customers(final @RequestParam String lastName) {
		log.info("calling find customer with parameter  lastName=" + lastName);

		return microservice2Service.getCustomers(lastName);

	}

}
