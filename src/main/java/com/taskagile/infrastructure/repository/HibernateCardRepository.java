package com.taskagile.infrastructure.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HibernateCardRepository extends HibernateSupport<Card> implements CardRepository {
}
