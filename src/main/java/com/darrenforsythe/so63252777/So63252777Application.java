package com.darrenforsythe.so63252777;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class So63252777Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(So63252777Application.class);

  public static void main(String[] args) {
    SpringApplication.run(So63252777Application.class, args);
  }

  @Bean
  UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }

  @Bean
  ApplicationRunner insertAndLogUser(UserService userService) {
    return args -> LOGGER.info("saved - {}", userService.newUser("Darren", "fajita"));
  }
}
