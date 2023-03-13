package com.redis.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

  @GetMapping("/login")
  public String login(HttpSession session, String name) {
    session.setAttribute("name",name);
    return "saved.";
  }

  @GetMapping("/myName")
  public String getMyName(HttpSession session) {
    String name = (String) session.getAttribute("name");
    return name;
  }
}
