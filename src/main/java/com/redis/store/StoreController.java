package com.redis.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController {

  @Value("${redis.key.fruit}")
  private String fruit;


  @Autowired
  StringRedisTemplate redisTemplate;

  @PostMapping("/fruit")
  public String registerFruit(@RequestParam String name) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    ops.set(fruit, name);
    return "saved";
  }

  @GetMapping("/fruit")
  public String getFruit() {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    return ops.get(fruit);
  }
}
