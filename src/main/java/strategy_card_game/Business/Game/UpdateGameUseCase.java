package strategy_card_game.Business.Game;

import strategy_card_game.Domain.Game.UpdateGameRequest;

public interface UpdateGameUseCase {
    void updateGame(UpdateGameRequest request);
}
