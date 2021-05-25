package com.taskagile.web.apis.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taskagile.utils.JsonUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public AuthenticationFilter() {
    super(new AntPathRequestMatcher("/api/authentications", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    log.debug("Processing login request");

    String requestBody = IOUtils.toString(request.getReader());
    LoginRequest loginRequest = JsonUtils.toObject(requestBody, LoginRequest.class);
    if (loginRequest == null || loginRequest.isInvalid()) {
      throw new InsufficientAuthenticationException("Invalid authentication request");
    }

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password);
    return this.getAuthenticationManager().authenticate(token);
  }

  @Setter
  @Getter
  static class LoginRequest {
    private String username;
    private String password;

    public boolean isInvalid() {
      return !StringUtils.hasText(username) || !StringUtils.hasText(password);
    }
  }
}
