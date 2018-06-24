package com.fathzer.localization.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CorsConfig {
	public static final String ORIGINS;
	
	static {
		String origins = System.getenv("CORS_ORIGINS");
		ORIGINS = origins==null ? "*" : origins;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				if (ORIGINS.isEmpty()) {
					log.info("CORS is disabled");
				} else {
					log.info("Following CORS origins are allowed {}",ORIGINS);
					registry.addMapping("/**").allowedOrigins(ORIGINS.split(","));
				}
			}
		};
	}
}
