package strategy_card_game.Business;

import strategy_card_game.Domain.CreateCardRequest;
import strategy_card_game.Domain.CreateCardResponse;

public interface CreateCardUseCase {
    CreateCardResponse createCard(CreateCardRequest request);
}
