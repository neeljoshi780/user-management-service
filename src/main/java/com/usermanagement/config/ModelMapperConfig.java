package com.usermanagement.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Global ModelMapper configuration.
 * Provides a singleton bean for mapping across application.
 */
@Configuration
public class ModelMapperConfig {

	/**
	 * Creates and configures ModelMapper bean.
	 * Enables strict matching and skips null values.
	 *
	 * @return configured ModelMapper instance
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		// configurations
		mapper.getConfiguration()
			.setSkipNullEnabled(true)   // skip null values
			.setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return mapper;
	}

}