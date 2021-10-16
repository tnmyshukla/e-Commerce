package com.grail.ecom.service;

import com.grail.ecom.model.CustomUserDetails;
import com.grail.ecom.model.User;
import com.grail.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user=userRepository.findUserByEmail(email);
    user.orElseThrow(()->new UsernameNotFoundException("User not present"));
    return user.map(CustomUserDetails::new).get();
  }
}
