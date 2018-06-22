package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.AbstractEnvironment;

import com.example.api.config.SwaggerConfig;
import com.example.api.config.WebConfig;

import io.swagger.models.Swagger;

@SpringBootApplication
@Import({SwaggerConfig.class,WebConfig.class})
public class Application {
	public static void main(String[]args) {
		//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"prod"); // for using application-prod.properties
		SpringApplication.run(Application.class, args);
	}
}
