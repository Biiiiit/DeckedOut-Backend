package strategy_card_game.Business.Card;

import strategy_card_game.Domain.Card.UpdateCardRequest;

public interface UpdateCardUseCase {
    void updateCard(UpdateCardRequest request);
}
