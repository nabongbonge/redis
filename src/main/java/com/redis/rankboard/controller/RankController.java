package com.redis.rankboard.controller;

import com.redis.rankboard.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankController {

  private final RankService rankService;

  @PostMapping("/rank")
  public Boolean setScore(@RequestParam String userId, @RequestParam int score) {
    return rankService.setUserScorce(userId, score);
  }

  @GetMapping("/rank/{userId}")
  public Long getUserRank(@PathVariable String userId) {
    return rankService.getUserRanking(userId);
  }

  @GetMapping("/rank/top/{limit}")
  public List<String> getTopRank(@PathVariable int limit) {
    return rankService.getTopRanks(limit);
  }
}
