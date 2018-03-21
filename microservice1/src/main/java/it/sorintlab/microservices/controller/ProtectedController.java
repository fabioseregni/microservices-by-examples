package it.sorintlab.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {

	@Autowired
	private OAuth2RestTemplate oauth2RestClient;

	@GetMapping("/my-protected-resource")
	public ResponseEntity<String> protectedResource() {
		return oauth2RestClient.getForEntity("http://localhost:9094/", String.class);
	}

}
