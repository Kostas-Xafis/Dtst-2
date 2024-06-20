package gr.hua.dit.springproject.Config;

import gr.hua.dit.springproject.DtstProjectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBLoader {

    private Logger logger = LoggerFactory.getLogger(DtstProjectApplication.class);

    @Value("${spring.datasource.url}")
    private String connString;

    @Bean
    private void databaseLoader() {
        try {
            Connection conn = DriverManager.getConnection(connString);
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.addScript(new ClassPathResource("dummies.sql"));
            databasePopulator.populate(conn);
            conn.close();
        } catch (SQLException e) {
            logger.error("Either failed to connect to database or db is already initialized with dummy data: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to load dummy data: " + e.getMessage());
        }
    }
}
