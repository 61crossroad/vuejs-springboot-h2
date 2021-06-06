package com.taskagile.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
public class ApplicationProperties {
  @Email
  @NotBlank
  private String mailFrom;
}
