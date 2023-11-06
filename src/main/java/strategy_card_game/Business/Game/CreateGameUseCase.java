package strategy_card_game.Business.Game;

import strategy_card_game.Domain.Game.CreateGameRequest;
import strategy_card_game.Domain.Game.CreateGameResponse;

public interface CreateGameUseCase {
    CreateGameResponse createGame(CreateGameRequest request);
}
