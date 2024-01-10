package strategy_card_game.Business.Game;

import strategy_card_game.Domain.Game.GetAllGamesRequest;
import strategy_card_game.Domain.Game.GetAllGamesResponse;
import strategy_card_game.Domain.Game.GetDeveloperGamesRequest;

public interface GetGamesByDeveloper {
    GetAllGamesResponse getGamesByDeveloper(GetDeveloperGamesRequest request);
}
