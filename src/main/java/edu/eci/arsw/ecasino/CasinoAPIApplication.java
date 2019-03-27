package edu.eci.arsw.ecasino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel Vela 
 */
@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.ecasino"})
public class CasinoAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasinoAPIApplication.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

}