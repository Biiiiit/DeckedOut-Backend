package strategy_card_game.Business.Game;

import strategy_card_game.Domain.Game.Game;

import java.util.Optional;

public interface GetGameUseCase {
    Optional<Game> getGame(long gameId);
}
