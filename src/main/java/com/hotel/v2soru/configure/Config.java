package com.hotel.v2soru.configure;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class Config implements WebMvcConfigurer {
	
	@Bean
	public OpenAPI swaggerDocOpenApi() {
		
		Server devserver = new Server();
		devserver.setUrl("localhost:8080");
		devserver.setDescription("Development Server");
		
		Server testserver = new Server();
		testserver.setUrl("localhost:8081");
		testserver.setDescription("Testing Server");
		
		Contact contact = new Contact();
		contact.setName("Mathiyalagan");
		contact.setEmail("mathiyalagan930@gmail.com");
		contact.setUrl("https://github.com/mathiyalagan-murugaiyan");
		
		License license = new License();
		license.setName("License");
		license.setUrl("License Provide");
		
	    Info info = new Info();
	    info.setContact(contact);
	    info.setLicense(license);
	    info.setDescription("Hotel Application `v2soru`");
	    
	    info.setTermsOfService("www.wikipedia.com");
	    info.setTitle("Hotel Application");
	    info.setVersion("2.0");
	    
	    OpenAPI op = new OpenAPI();
	    op.setInfo(info);
	    op.setServers(Arrays.asList(devserver,testserver));
	    
		
		return op;
		
	}

}
