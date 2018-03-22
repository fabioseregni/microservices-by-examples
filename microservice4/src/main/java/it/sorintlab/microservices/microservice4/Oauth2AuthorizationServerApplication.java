package it.sorintlab.microservices.microservice4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class Oauth2AuthorizationServerApplication {

	@Value("${message:I'm Protected resource}")
	private String message;

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServerApplication.class, args);
	}

	@RequestMapping("/")
	public @ResponseBody ProtectedModel home() {
		return new ProtectedModel(this.message);
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatcher(
					new OrRequestMatcher(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/admin/beans")))
					.authorizeRequests().anyRequest().access("#oauth2.hasScope('read')");
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.tokenServices(remoteTokenServices()).resourceId("protected-resource");
		}

		@Bean
		public RemoteTokenServices remoteTokenServices() {
			final RemoteTokenServices tokenServices = new RemoteTokenServices();
			tokenServices.setCheckTokenEndpointUrl("http://localhost:10002/oauth/check_token");
			tokenServices.setClientId("resource-server");
			tokenServices.setClientSecret("resource-server");
			return tokenServices;
		}

	}
}
