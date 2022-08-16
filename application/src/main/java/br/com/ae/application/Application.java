package br.com.ae.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.ae")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
