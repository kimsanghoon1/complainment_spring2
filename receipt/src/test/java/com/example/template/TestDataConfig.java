package com.example.template;

import complainment.domain.Complainment;
import complainment.domain.ComplainmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDataConfig {

    @Bean
    public CommandLineRunner initData(ComplainmentRepository repository) {
        return args -> {
            Complainment complainment = new Complainment();
            complainment.setId(1L);
            complainment.set("1");
            complainment.setID("user1");
            complainment.set("[object Object]");
            repository.save(complainment);
        };
    }
}
