package com.darrenforsythe.so63252777;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers(disabledWithoutDocker = true)
class BestUserServiceTest {

  private UserService userService;

  @BeforeEach
  void setup(@Autowired UserRepository userRepository) {
    userService = new UserService(userRepository);
  }

  @Container private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>();

  @DynamicPropertySource
  static void setMySqlProperties(DynamicPropertyRegistry properties) {
    properties.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
    properties.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    properties.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
  }

  @Test
  void saveUser(@Autowired UserRepository userRepository) {
    String name = "Steve";
    String food = "Apple";

    User savedUser = userService.newUser(name, food);
    assertEquals(savedUser.getName(), name);
    assertEquals(savedUser.getFood(), food);


    //Quick example to flush to DB and count number of saved records.
    userRepository.flush();

    assertEquals(1, userRepository.count());
  }
}
