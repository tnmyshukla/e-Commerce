package com.grail.ecom.controller;

import com.grail.ecom.repository.RoleRepository;
import com.grail.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;

  @GetMapping("/login")
  public String login(){
    return "login";
  }
  @GetMapping("/register")
  public String registerGet(){
    return "register";
  }


}
