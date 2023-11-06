package strategy_card_game.Business.Game;

import strategy_card_game.Domain.Game.GetAllGamesRequest;
import strategy_card_game.Domain.Game.GetAllGamesResponse;

public interface GetGamesUseCase {
    GetAllGamesResponse getGames(GetAllGamesRequest request);
}
