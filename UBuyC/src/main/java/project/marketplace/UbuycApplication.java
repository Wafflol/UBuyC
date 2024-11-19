package project.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
// @EnableAutoConfiguration
// @Configuration
// @ComponentScan("java")
public class UbuycApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbuycApplication.class, args);
	}
}
