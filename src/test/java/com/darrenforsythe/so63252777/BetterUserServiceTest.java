package com.darrenforsythe.so63252777;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BetterUserServiceTest {

  private UserService userService;

  @BeforeEach
  void setup(@Autowired UserRepository userRepository) {
    userService = new UserService(userRepository);
  }

  @Test
  void saveUser() {
    String name = "Steve";
    String food = "Apple";

    User savedUser = userService.newUser(name, food);

    assertEquals(savedUser.getName(), name);
    assertEquals(savedUser.getFood(), food);
  }
}
