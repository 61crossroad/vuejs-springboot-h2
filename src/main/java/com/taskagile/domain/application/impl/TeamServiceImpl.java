package com.taskagile.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.domain.application.TeamService;
import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.team.events.TeamCreatedEvent;
import com.taskagile.domain.model.user.UserId;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;
  private final DomainEventPublisher domainEventPublisher;

  @Override
  public List<Team> findTeamsByUserId(UserId userId) {
    return teamRepository.findTeamsByUserId(userId);
  }

  @Override
  public Team createTeam(CreateTeamCommand command) {
    Team team = Team.create(command.getName(), command.getUserId());
    teamRepository.save(team);
    domainEventPublisher.publish(new TeamCreatedEvent(this, team));
    return team;
  }

}
