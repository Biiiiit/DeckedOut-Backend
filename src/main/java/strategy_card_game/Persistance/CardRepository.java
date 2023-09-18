package strategy_card_game.Persistance;

import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.List;

public interface CardRepository {
    boolean existsByName(String name);

    List<CardEntity> findAllByType(String type);

    CardEntity save(CardEntity card);

    void deleteById(long cardId);

    List<CardEntity> findAll();

}
