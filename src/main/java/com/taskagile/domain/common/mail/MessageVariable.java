package com.taskagile.domain.common.mail;

import java.util.Objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MessageVariable {
  private String key;
  private Object value;

  private MessageVariable(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public static MessageVariable from(String key, Object value) {
    return new MessageVariable(key, value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MessageVariable)) return false;
    MessageVariable that = (MessageVariable) o;
    return Objects.equals(key, that.key) &&
      Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }
}
