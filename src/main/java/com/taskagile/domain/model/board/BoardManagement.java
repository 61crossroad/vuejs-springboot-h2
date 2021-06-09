package com.taskagile.domain.model.board;

import com.taskagile.domain.model.team.TeamId;
import com.taskagile.domain.model.user.UserId;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BoardManagement {
  private final BoardRepository boardRepository;
  private final BoardMemberRepository boardMemberRepository;

  public Board createBoard(UserId creatorId, String name, String description, TeamId teamId) {
    Board board = Board.create(creatorId, name, description, teamId);
    boardRepository.save(board);
    BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
    boardMemberRepository.save(boardMember);
    return board;
  }
}
