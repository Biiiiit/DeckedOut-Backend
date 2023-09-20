package strategy_card_game.Business;

import strategy_card_game.Domain.UpdateCardRequest;

public interface UpdateCardUseCase {
    void updateCard(UpdateCardRequest request);
}
