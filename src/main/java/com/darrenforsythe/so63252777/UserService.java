package com.darrenforsythe.so63252777;

public class UserService {

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public final UserRepository userRepository;

  public User newUser(String name, String food) {
    var user = new User();
    user.setName(name);
    user.setFood(food);
    return userRepository.save(user);
  }
}
