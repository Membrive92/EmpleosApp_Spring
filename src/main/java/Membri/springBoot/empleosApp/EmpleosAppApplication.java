package Membri.springBoot.empleosApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EmpleosAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleosAppApplication.class, args);
	}

}
