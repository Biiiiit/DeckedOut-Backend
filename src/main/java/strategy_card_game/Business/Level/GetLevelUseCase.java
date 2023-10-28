package strategy_card_game.Business.Level;

import strategy_card_game.Domain.Level.Level;

import java.util.Optional;

public interface GetLevelUseCase {
    Optional<Level> getLevel(long levelId);
}
