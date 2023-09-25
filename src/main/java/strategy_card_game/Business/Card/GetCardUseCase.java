package strategy_card_game.Business.Card;

import strategy_card_game.Domain.Card.Card;

import java.util.Optional;

public interface GetCardUseCase {
    Optional<Card> getCard(long cardID);
}
