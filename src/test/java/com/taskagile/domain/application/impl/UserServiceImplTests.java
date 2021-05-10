package com.taskagile.domain.application.impl;

import static org.mockito.Mockito.mock;

import com.taskagile.domain.model.user.RegistrationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceImplTests {
  private RegistrationManagement registrationManagementMock;
  private DomainEventPublisher eventPublisherMock;
  private MailManager mailManagerMock;
  private UserServiceImpl instance;

  @BeforeEach
  public void setUp() {
    registrationManagementMock = mock (RegistrationManagement.class);
    eventPublisherMock = mock (DomainEventPublisher.class);
    mailManagerMock = mock (MailManager.class);
    instance = new UserServiceImpl(registrationServiceMock, eventPublisherMock, mailerMock);
  }

  @Test
  public void register_nullCommand_shouldFail() throws RegistrationException {
    Assertions.assertThrows(IllegalArgumentException.class, () -> instance.register(null));
  }
}
