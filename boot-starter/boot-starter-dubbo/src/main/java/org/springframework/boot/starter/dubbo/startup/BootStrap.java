package org.springframework.boot.starter.dubbo.startup;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:/spring/**/*.xml")
public class BootStrap {
	
	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(BootStrap.class);
		application.run(args);
	}
}
