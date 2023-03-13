package com.redis.cache.config.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

  public String getUserName(String userId) {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {

    }

    if("A".equals(userId)){
      return "adam";
    }
    if("B".equals(userId)){
      return "bob";
    }
    return "";
  }

  @Cacheable(cacheNames = "userAgeCache", key = "#userId")
  public int getUserAge(String userId) {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {

    }

    if("A".equals(userId)){
      return 30;
    }
    if("B".equals(userId)){
      return 31;
    }
    return 29;
  }
}
