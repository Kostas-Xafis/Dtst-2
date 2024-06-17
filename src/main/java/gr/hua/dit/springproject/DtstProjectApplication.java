package gr.hua.dit.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
public class DtstProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(DtstProjectApplication.class, args);
    }
}
