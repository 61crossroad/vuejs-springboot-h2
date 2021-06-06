package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.UserApplicationService;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

public class UserApplicationServiceImpl implements UserApplicationService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!StringUtils.hasText(username)) {
      throw new UsernameNotFoundException("No user found");
    }

    User user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }

    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }

    return new SimpleUser(user);
  }
}
