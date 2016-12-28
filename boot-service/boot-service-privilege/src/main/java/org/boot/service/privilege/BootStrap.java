package org.boot.service.privilege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ImportResource("classpath:/spring/**/*.xml")
public class BootStrap {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BootStrap.class);
		application.run(args);
	}
}
