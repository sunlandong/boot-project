package org.boot.service.core;

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
		//放在这里未统一放置是为测试
	    try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
