package com.taskagile.domain.application.commands;

import com.taskagile.domain.model.user.UserId;

import lombok.Getter;

@Getter
public class CreateTeamCommand {

  private UserId userId;
  private String name;

  public CreateTeamCommand(UserId userId, String name) {
    this.userId = userId;
    this.name = name;
  }
}
