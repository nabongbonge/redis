package com.redis.cache.config.controller;

import com.redis.cache.config.dto.UserProfile;
import com.redis.cache.config.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @Autowired
  private UserService userService;

  @GetMapping("/user/{userId}/profile")
  public UserProfile getUserProfile(@PathVariable(value = "userId") String userId) {
    return userService.getUserProfile(userId);
  }
}
