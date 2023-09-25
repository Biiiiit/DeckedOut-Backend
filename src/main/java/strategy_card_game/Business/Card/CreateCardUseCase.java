package strategy_card_game.Business.Card;

import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.CreateCardResponse;

public interface CreateCardUseCase {
    CreateCardResponse createCard(CreateCardRequest request);
}
