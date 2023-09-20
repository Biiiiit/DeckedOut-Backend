package strategy_card_game.Business;

import strategy_card_game.Domain.Card;

import java.util.Optional;

public interface GetCardUseCase {
    Optional<Card> getCard(long cardID);
}
