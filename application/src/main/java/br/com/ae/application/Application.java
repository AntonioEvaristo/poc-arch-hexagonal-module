package br.com.ae.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ae")
@EntityScan(basePackages = "br.com.ae.integration")
@EnableJpaRepositories(basePackages = "br.com.ae.integration")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
