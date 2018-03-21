package it.sorintlab.microservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.hystrix.FallbackFactory;
import it.sorintlab.microservices.model.Customer;
import it.sorintlab.microservices.service.Microservice2Service.MicroserviceFallbackFactory;

@FeignClient(name = "microservice2", value = "microservice2", fallbackFactory = MicroserviceFallbackFactory.class)
public interface Microservice2Service {

	@RequestMapping(method = RequestMethod.GET, value = "/customers")
	Customer[] getCustomers(final @RequestParam("lastName") String lastName);

	@Component
	static class MicroserviceFallbackFactory implements FallbackFactory<Microservice2Service> {
		private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceFallbackFactory.class);

		@Override
		public Microservice2Service create(Throwable cause) {
			return new Microservice2Service() {
				@Override
				public Customer[] getCustomers(final String lastName) {
					MicroserviceFallbackFactory.LOGGER.info("fallback; reason was: {}, {}", cause.getMessage(), cause);
					return new Customer[] {};
				}
			};
		}
	}

}
