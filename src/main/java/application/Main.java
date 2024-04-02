package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Главный класс приложения.
 */
@SpringBootApplication
@EnableJpaRepositories(value = "application.repositories")
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
