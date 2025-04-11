package io.github.https418.authy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.github.https418.authy")
public class AuthyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthyApplication.class, args);
    }

}
