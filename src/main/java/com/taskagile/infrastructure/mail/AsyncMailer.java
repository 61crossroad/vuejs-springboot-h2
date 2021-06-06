package com.taskagile.infrastructure.mail;

import com.taskagile.domain.common.mail.Mailer;
import com.taskagile.domain.common.mail.Message;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AsyncMailer implements Mailer {

  private final JavaMailSender mailSender;

  @Async
  @Override
  public void send(Message message) {
    Assert.notNull(message, "Parameter `message` must not be null");

    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();

      if (StringUtils.hasText(message.getFrom())) {
        mailMessage.setFrom(message.getFrom());
      }
      if (StringUtils.hasText(message.getSubject())) {
        mailMessage.setSubject(message.getSubject());
      }
      if (StringUtils.hasText(message.getBody())) {
        mailMessage.setText(message.getBody());
      }
      if (message.getTo() != null) {
        mailMessage.setTo(message.getTo());
      }

      mailSender.send(mailMessage);
    } catch (MailException e) {
      log.error("Failed to send mail message", e);
    }
  }
}
