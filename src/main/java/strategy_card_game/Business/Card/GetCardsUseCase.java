package strategy_card_game.Business.Card;

import strategy_card_game.Domain.Card.GetAllCardsRequest;
import strategy_card_game.Domain.Card.GetAllCardsResponse;

public interface GetCardsUseCase {

    GetAllCardsResponse getCards(GetAllCardsRequest request);
}
