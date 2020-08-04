package com.darrenforsythe.so63252777;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class UserServiceTest {

  private UserService userService;
  private UserRepository userRepository;

  private static User savedUser;

  @BeforeEach
  void setup() {
    userRepository = createMockRepo();
    userService = new UserService(userRepository);
  }

  @Test
  void testSaveUser(){
    String name = "Steve";
    String food = "Apple";

    userService.newUser(name, food);

    assertEquals(savedUser.getName(), name);
    assertEquals(savedUser.getFood(), food);
  }

  public UserRepository createMockRepo() {
    UserRepository mockRepo = mock(UserRepository.class);
    try {
      doAnswer(
              (Answer<Void>) invocation -> {
                savedUser = (User) invocation.getArguments()[0];
                return null;
              })
          .when(mockRepo)
          .save(any(User.class));
    } catch (Exception e) {
    }
    return mockRepo;
  }
}
