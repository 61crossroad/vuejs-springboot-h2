package com.taskagile.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.domain.application.BoardService;
import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.board.Board;
import com.taskagile.domain.model.board.BoardManagement;
import com.taskagile.domain.model.board.events.BoardCreatedEvent;
import com.taskagile.domain.model.user.UserId;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final BoardManagement boardManagement;
  private DomainEventPublisher domainEventPublisher;

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    return boardRepository.findBoardsByMembership(userId);
  }

  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = boardManagement.createBoard(command.getUserId(), command.getName(),
      command.getDescription(), command.getTeamId());
    domainEventPublisher.publish(new BoardCreatedEvent(this, board));
    return board;
  }
}
