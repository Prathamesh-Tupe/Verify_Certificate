package com.atm.atmAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
@EnableJpaRepositories(basePackages = "com.repository")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("Hello Spring...!");
	}
	
	@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/uploads/**")
	                .addResourceLocations(
	                  "file:C:/Users/PRATHAMESH/Downloads/atmAPI/atmAPI/uploads/"
	                );
	    }
	}


}
