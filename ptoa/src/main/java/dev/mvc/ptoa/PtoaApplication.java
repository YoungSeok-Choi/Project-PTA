package dev.mvc.ptoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class PtoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtoaApplication.class, args);
	}

}
