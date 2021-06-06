package com.taskagile.domain.common.mail;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DefaultMailManager implements MailManager {

  @Override
  public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
    log.info("[send email] {} -> {}", emailAddress, subject);
  }
}
