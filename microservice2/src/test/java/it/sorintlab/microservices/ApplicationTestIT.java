package it.sorintlab.microservices;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import it.sorintlab.microservices.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestConfiguration
public class ApplicationTestIT {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void shoudGetCustomerByLastName() {
		ResponseEntity<Customer[]> entity = restTemplate.getForEntity("/customers?lastName={lastName}",Customer[].class, "Seregni");
		
		assertNotNull(entity.getBody());
		assertThat(entity.getBody().length, equalTo(1));
	}
	

}
