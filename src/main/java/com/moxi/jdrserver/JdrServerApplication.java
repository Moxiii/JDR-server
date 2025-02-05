package com.moxi.jdrserver;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
public class JdrServerApplication {

    private final UserRepository userRepository;

    public JdrServerApplication(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_HOST", Objects.requireNonNull(dotenv.get("DB_HOST")));
        System.setProperty("DB_USER", Objects.requireNonNull(dotenv.get("DB_USER")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        SpringApplication.run(JdrServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultDataInitializer() {
        return args -> {
            if (userRepository.count() == 0) {
                User Charles = new User("drizzy", "ee");
                User Marco = new User("marco", "ee");
                User Tim = new User("tim", "ee");
                User Lucas = new User("SummyFrog", "ee");
                userRepository.save(Charles);
                userRepository.save(Marco);
                userRepository.save(Tim);
                userRepository.save(Lucas);
            }
        };
    }
}
