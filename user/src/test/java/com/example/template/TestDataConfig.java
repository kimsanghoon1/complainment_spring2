package com.example.template;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import complainment.domain.User;
import complainment.domain.UserRepository;


@TestConfiguration
public class TestDataConfig {

    @Bean
    public CommandLineRunner initData(UserRepository repository) {
        return args -> {
            User user = new User();
            user.setResidentNumber("111111-1111111");
            repository.save(user);
        };
    }
}