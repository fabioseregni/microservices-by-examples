package com.phoenix.microservices;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
public class EurekaConfig {

	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}

}
