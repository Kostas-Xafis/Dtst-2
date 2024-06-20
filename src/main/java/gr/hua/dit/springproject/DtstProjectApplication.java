package gr.hua.dit.springproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DtstProjectApplication {
    private static final Logger log = LoggerFactory.getLogger(DtstProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DtstProjectApplication.class, args);
    }
}
