package com.redis.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfig {

  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

    /**
     * 캐시에 대해서 디플트 설정
     */
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(10)) // 기본 TTL
            .computePrefixWith(CacheKeyPrefix.simple())
            .serializeKeysWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
            );

    /**
     * userAgeCache 캐시에 대해서만 TTL을 5초로 지정
     */
    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
    configMap.put("userAgeCache", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5))); // 특정 캐시에 대한 TTL

    return RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .cacheDefaults(configuration)
            .withInitialCacheConfigurations(configMap)
            .build();
  }
}
