package edu.eci.arsw.ecasino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.eci.arsw.ecasino" })
public class CasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasinoApplication.class, args);
	}

}
