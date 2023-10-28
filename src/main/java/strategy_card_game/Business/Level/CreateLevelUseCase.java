package strategy_card_game.Business.Level;

import strategy_card_game.Domain.Level.CreateLevelRequest;
import strategy_card_game.Domain.Level.CreateLevelResponse;

public interface CreateLevelUseCase {
    CreateLevelResponse createLevel(CreateLevelRequest request);
}
