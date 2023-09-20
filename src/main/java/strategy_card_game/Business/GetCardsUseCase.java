package strategy_card_game.Business;

import strategy_card_game.Domain.GetAllCardsRequest;
import strategy_card_game.Domain.GetAllCardsResponse;

public interface GetCardsUseCase {

    GetAllCardsResponse getCards(GetAllCardsRequest request);
}
