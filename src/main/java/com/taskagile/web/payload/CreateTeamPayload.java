package com.taskagile.web.payload;

import com.taskagile.domain.model.user.UserId;

import lombok.Setter;

@Setter
public class CreateTeamPayload {
  private String name;

  public CreateTeamCommand toCommand(UserId userId) {
    return new CreateTeamCommand(userId, name);
  }
}
