package edu.eci.arsw.ecasino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Daniel Vela 
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.cinema"})
public class CasinoAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasinoAPIApplication.class, args);
    }
}