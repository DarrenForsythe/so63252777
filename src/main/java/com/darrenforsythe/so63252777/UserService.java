package com.darrenforsythe.so63252777;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public final UserRepository userRepository;

  public User newUser(String name, String food) {
    var user = new User();
    user.setName(name);
    user.setFood(food);
    LOGGER.info("Saving User - {}", user);
    final User savedUser = userRepository.save(user);
    LOGGER.info("Saved User - {}", savedUser);
    return savedUser;
  }
}
