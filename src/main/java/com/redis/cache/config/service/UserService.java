package com.redis.cache.config.service;

import com.redis.cache.config.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

  @Autowired
  private ExternalApiService externalApiService;

  /**
   * Redis 사용을 위한 의존성 주입
   */
  @Autowired
  private StringRedisTemplate redisTemplate;

  /**
   * Cache-Aside 전략 적용
   */
  public UserProfile getUserProfile(String userId) {

    String userName = null;

    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    String cacheName = ops.get("nameKey:" + userId);

    if (cacheName != null) {
      userName = cacheName;
    }else {

    userName = externalApiService.getUserName(userId);

    // 캐시 5초 유지
    ops.set("nameKey:"+userId, userName, 5, TimeUnit.SECONDS);
    }

    // Spring에서 제공하는 캐싱 매커니즘을 통해 캐싱
    int userAge = externalApiService.getUserAge(userId);

    return new UserProfile(userName, userAge);
  }
}
