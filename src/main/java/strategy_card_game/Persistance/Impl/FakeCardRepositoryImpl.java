package strategy_card_game.Persistance.Impl;

import org.springframework.stereotype.Repository;
import strategy_card_game.Domain.Type;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class FakeCardRepositoryImpl implements CardRepository {
    private static long NEXT_ID = 1;
    private final List<CardEntity>savedCards;
    public FakeCardRepositoryImpl() {this.savedCards = new ArrayList<>();}

    @Override
    public boolean existsByName(String name) {
        return this.savedCards
                .stream()
                .anyMatch(cardEntity -> cardEntity.getName().equals(name));
    }

    @Override
    public List<CardEntity> findAllByType(String type) {
        Type cardType = Type.fromString(type);

        return this.savedCards
                .stream()
                .filter(cardEntity -> cardType == cardEntity.getType())
                .collect(Collectors.toList());
    }

    @Override
    public CardEntity save(CardEntity card) {
        if (card.getId() == null) {
            card.setId(NEXT_ID);
            NEXT_ID++;
            this.savedCards.add(card);
        }
        return card;
    }

    @Override
    public void deleteById(long cardId) {
        this.savedCards.removeIf(cardEntity -> cardEntity.getId().equals(cardId));
    }

    @Override
    public List<CardEntity> findAll() {
        return Collections.unmodifiableList(this.savedCards);
    }

    @Override
    public Optional<CardEntity> findById(long cardId) {
        return this.savedCards.stream()
                .filter(cardEntity -> cardEntity.getId().equals(cardId))
                .findFirst();
    }
}
