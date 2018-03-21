package it.sorintlab.microservices.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.sorintlab.microservices.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstName(String firstName);
}