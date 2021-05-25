package com.taskagile.domain.common.mail;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DefaultMailManager implements MailManager {

  @Override
  public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
    // TODO Auto-generated method stub
    log.info("[send email]\nemail: {}\nsubject: {}\ntemplate: {}\n[variables]\n", emailAddress, subject, template);
    Arrays.stream(variables).forEach(v -> log.info(v.getKey(), v.getValue().getClass().getName()));
  }
}
