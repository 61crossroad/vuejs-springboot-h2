package com.taskagile.domain.model.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class SimpleUser implements UserDetails {

  private static final long serialVersionUID = 6011150738930516395L;

  private UserId userId;
  private String username;
  private String password;

  public SimpleUser(User user) {
    this.userId = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
  }

  public UserId getUserId() {
    return userId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
