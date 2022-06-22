package com.fine_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FineServerApplication.class, args);
    }

}
