package com.phoenix.microservices;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestConfiguration
public class ApplicationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void shoudGetCustomerByLastName() {
		ResponseEntity<Customer[]> entity = restTemplate.getForEntity("/customers?lastName={lastName}",Customer[].class, "Bauer");
		
		assertNotNull(entity.getBody());
		assertThat(entity.getBody().length, equalTo(2));
	}
	

}
