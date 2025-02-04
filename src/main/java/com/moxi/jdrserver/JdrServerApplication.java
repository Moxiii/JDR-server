package com.moxi.jdrserver;

import com.moxi.jdrserver.Enums.ModuleType;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.Module;
import com.moxi.jdrserver.Repository.ModuleRepository;
import com.moxi.jdrserver.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JdrServerApplication {

    public JdrServerApplication(UserRepository userRepository, ModuleRepository moduleRepository) {
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdrServerApplication.class, args);
    }
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    @Bean
    public CommandLineRunner defaultDataInitializer(ModuleRepository moduleRepository) {
        return args -> {
            if (userRepository.count() == 0 ){
                User Charles = new User("drizzy" , "ee");
                User Marco = new User("marco" , "ee");
                User Tim = new User("tim" , "ee");
                User Lucas = new User("SummyFrog" , "ee");
                userRepository.save(Charles);
                userRepository.save(Marco);
                userRepository.save(Tim);
                userRepository.save(Lucas);

                Module Test = new Module("module test", "ceci est un module de test", Lucas, ModuleType.Scenario, false);
                this.moduleRepository.save(Test);
            }

        };
    }
}
