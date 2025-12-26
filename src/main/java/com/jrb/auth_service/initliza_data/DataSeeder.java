package com.jrb.auth_service.initliza_data;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.User;

import net.datafaker.Faker;

@Component
public class DataSeeder implements CommandLineRunner {
    static Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private UserRepository repository;
    private Faker faker;

    public DataSeeder(UserRepository repository) {
        this.repository = repository;
        this.faker = new Faker(Locale.of("es", "AR"));
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase());
            String password = faker.credentials().password();
            User temp = User.builder()
                    .firstName(firstName)
                    .lastname(lastName)
                    .email(email)
                    .password(password)
                    .build();
            repository.save(temp);
        }
    }
}
