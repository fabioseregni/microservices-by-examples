package it.sorintlab.microservices.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.sorintlab.microservices.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public List<Customer> findByLastName(String lastName);

}