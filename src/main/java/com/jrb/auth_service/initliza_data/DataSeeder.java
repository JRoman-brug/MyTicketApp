package com.jrb.auth_service.initliza_data;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;

import net.datafaker.Faker;

@Component
public class DataSeeder implements CommandLineRunner {
    static Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private Faker faker;
    private UserRepository repository;
    private PasswordEncoder encoder;

    public DataSeeder(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
        this.faker = new Faker(Locale.of("es", "AR"));
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity userTest = UserEntity.builder()
                .firstName("roman")
                .lastname("brugnoni")
                .email("test@gmail.com")
                .password(encoder.encode("123456"))
                .build();
        repository.save(userTest);
        for (int i = 0; i < 10; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase());
            String password = faker.credentials().password();
            String hashedPassword = encoder.encode(password);
            UserEntity temp = UserEntity.builder()
                    .firstName(firstName)
                    .lastname(lastName)
                    .email(email)
                    .password(hashedPassword)
                    .build();
            repository.save(temp);
        }
    }
}
