package com.taskagile.domain.model.cardList;

import java.util.List;

import com.taskagile.domain.model.board.BoardId;

public interface CardListRepository {

  List<CardList> findByBoardId(BoardId boardId);

  void save(CardList cardList);

  void changePosition(List<CardListPosition> cardListPositions);

}
