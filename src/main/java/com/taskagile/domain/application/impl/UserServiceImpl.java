package com.taskagile.domain.application.impl;

import javax.transaction.Transactional;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Transactional
@Service
public class UserServiceImpl implements UserService {
  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
  }
}
