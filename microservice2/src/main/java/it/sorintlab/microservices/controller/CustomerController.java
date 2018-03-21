package it.sorintlab.microservices.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.sorintlab.microservices.Application;
import it.sorintlab.microservices.model.Customer;
import it.sorintlab.microservices.service.CustomerService;

@RestController
public class CustomerController {

	private static Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "getCustomersByLastName", nickname = "getCustomersByLastName")
	@RequestMapping(method = RequestMethod.GET, path = "/customers", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lastName", value = "Customer last name", required = true, dataType = "string", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Customer[].class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })

	@GetMapping("/customers")
	@ResponseBody
	public List<Customer> findCustomers(final @RequestParam String lastName) {
		return customerService.findByLastName(lastName);
	}

	@PostMapping("/customers")
	public @ResponseBody ResponseEntity<Customer> addCustomer(final @RequestBody Customer customer) {
		Customer savedCustomer = customerService.save(customer);
		log.info("Added customer : " + savedCustomer);
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

}
