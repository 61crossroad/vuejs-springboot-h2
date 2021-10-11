package com.taskagile.domain.model.card;

public class CardPosition {

  private long cardListId;
  private long cardId;
  private int position;

  public CardListId getCardListId() {
    return new CardListId(cardListId);
  }
}
