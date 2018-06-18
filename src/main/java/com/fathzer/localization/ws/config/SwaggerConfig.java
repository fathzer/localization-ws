package com.fathzer.localization.ws.config;

import java.util.Collections;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fathzer.localization.ws.controller.MessagesController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//TODO Change URL of swagger to something cleaner
	@Bean
	public Docket api() {
		ApiInfo info = new ApiInfo("Resource bundle API","description", getVersion(), null, null, null, null, Collections.emptyList());
		return new Docket(DocumentationType.SWAGGER_2)
				// Remove the default error code in responses section
				.useDefaultResponseMessages(false)
				.apiInfo(info)
				.select()
				// Remove internal services /error
				.apis(RequestHandlerSelectors.basePackage(MessagesController.class.getPackage().getName()))              
				.paths(PathSelectors.any())                          
				.build();                                           
	}

	private static String getVersion() {
		return ResourceBundle.getBundle("swagger").getString("version");
	}
}