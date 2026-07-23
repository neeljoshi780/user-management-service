package com.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class CorsConfig {

	/**
	 * Configures global CORS settings for the application.
	 *
	 * @return configured WebMvcConfigurer instance
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {

			/**
			 * Registers global CORS mappings.
			 *
			 * @param registry CORS registry
			 */
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

}