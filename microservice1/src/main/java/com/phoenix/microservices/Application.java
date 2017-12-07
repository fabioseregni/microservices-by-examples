package com.phoenix.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableEurekaClient 
public class Application {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/customers")
	public @ResponseBody Customer[] customers(final @RequestParam String lastName) {
		log.info("calling find customer 1");
		ResponseEntity<Customer[]> customersEntity = restTemplate
				.getForEntity("http://microservice2/customers?lastName={lastName}", Customer[].class, lastName);
		return customersEntity.getBody();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }
}
