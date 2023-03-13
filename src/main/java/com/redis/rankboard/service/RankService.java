package com.redis.rankboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankService {

  @Value("${redis.key.rank}")
  private String rank;
  @Autowired
  StringRedisTemplate redisTemplate;

  /**
   * Redis 값 세팅
   */
  public boolean setUserScorce(String userId, int score) {
    ZSetOperations zSetOps = redisTemplate.opsForZSet(); // SortedSet을 위한 오퍼레이션
    return zSetOps.add(rank, userId, score);
  }

  /**
   * 순위 조회
   */
  public Long getUserRanking(String userId) {
    ZSetOperations zSetOps = redisTemplate.opsForZSet();
    return zSetOps.reverseRank(rank, userId);
  }

  /**
   * 범위 기반 조회
   */
  public List<String> getTopRanks(int limit) {
    ZSetOperations zSetOps = redisTemplate.opsForZSet();
    Set<String> rangeSet = zSetOps.reverseRange(rank, 0, limit - 1);

    return new ArrayList<>(rangeSet);
  }
}
